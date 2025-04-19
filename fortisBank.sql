-- TABLE DES UTILISATEURS
CREATE TABLE T_USER (
    USR_ID       INT            CONSTRAINT USR_ID_PK PRIMARY KEY,
    USR_NOM      VARCHAR2(50)   CONSTRAINT USR_NOM_NN NOT NULL,
    USR_PRENOM   VARCHAR2(50)   CONSTRAINT USR_PRENOM_NN NOT NULL,  
    USR_ADRESSE  VARCHAR2(100)  CONSTRAINT USR_ADRESSE_NN NOT NULL,
    USR_EMAIL    VARCHAR2(100)  UNIQUE CONSTRAINT CLI_EMAIL_NN NOT NULL,
    USR_NIP      VARCHAR2(10)   CONSTRAINT USR_NIP_NN NOT NULL,
    USR_ROLE     VARCHAR2(20)   CONSTRAINT USR_ROLE_NN NOT NULL CHECK (USR_ROLE IN ('CLIENT', 'GESTIONNAIRE'))
)

-- TABLE DES CLIENT
CREATE TABLE T_CLIENT (
    CLI_ID       INT CONSTRAINT CLI_ID_PK PRIMARY KEY,
    CLI_TEL      VARCHAR2(15) CONSTRAINT CLI_TEL_NN NOT NULL,
    CONSTRAINT CLI_USR_ID_FK FOREIGN KEY(CLI_ID) REFERENCES T_USER(USR_ID) ON DELETE CASCADE,
    CONSTRAINT CLI_TEL_UQ UNIQUE (CLI_TEL)

)

-- TABLE DES GESTINNAIRE
CREATE TABLE T_GESTIONNAIRE (
    GEST_ID      INT CONSTRAINT GEST_ID_PK PRIMARY KEY,
    CONSTRAINT GEST_USR_ID_FK FOREIGN KEY(GEST_ID) REFERENCES T_USER(USR_ID) ON DELETE CASCADE
)

-- procedure pour inserer un utilisateur dans la bd
CREATE OR REPLACE PROCEDURE insert_user(nom         IN T_USER.USR_NOM%TYPE,
                                        prenom      IN T_USER.USR_PRENOM%TYPE,
                                        adresse     IN T_USER.USR_ADRESSE%TYPE,
                                        email       IN T_USER.USR_EMAIL%TYPE,
                                        nip         IN T_USER.USR_NIP%TYPE,
                                        current_role        IN T_USER.USR_ROLE%TYPE,
                                        telephone   IN T_CLIENT.CLI_TEL%TYPE DEFAULT NULL
                                        ) AS
        id T_USER.USR_ID%TYPE; 
    BEGIN 
        id := SEQ_USER_ID.NEXTVAL;
        INSERT INTO T_USER(USR_ID, USR_NOM, USR_PRENOM, USR_ADRESSE,USR_EMAIL, USR_NIP, USR_ROLE)
        VALUES(id,nom,prenom,adresse,email,nip,current_role);
    
        IF current_role = 'CLIENT' THEN
            IF telephone IS NULL THEN
                RAISE_APPLICATION_ERROR(-20001, 'telephone doit etre inclut dans la creation dun client');
            END IF;

            INSERT INTO T_CLIENT(CLI_ID, CLI_TEL)
            VALUES(id,telephone);
            --dans le cas de compte cheque la limite
            insert_compte('CHEQUE',0 ,id ,null ,null ,null, 0.05,2);
        ELSE
            INSERT INTO T_GESTIONNAIRE(GEST_ID)
            VALUES(id);
        END IF;
    
    END;
/

-- procedure pour update un utilisateur dans la bd
CREATE OR REPLACE PROCEDURE update_user(nom         IN T_USER.USR_NOM%TYPE,
                                        prenom      IN T_USER.USR_PRENOM%TYPE,
                                        adresse     IN T_USER.USR_ADRESSE%TYPE,
                                        email       IN T_USER.USR_EMAIL%TYPE,
                                        nip         IN T_USER.USR_NIP%TYPE,
                                        telephone   IN T_CLIENT.CLI_TEL%TYPE DEFAULT NULL
                                        ) AS
        id T_USER.USR_ID%TYPE;
        current_role T_USER.USR_ROLE%TYPE;
    BEGIN 
        SELECT USR_ID, USR_ROLE INTO id, current_role FROM T_USER WHERE USR_EMAIL = email;
                            --NVL verifie si la valeur est null
        UPDATE T_USER SET   USR_NOM     = NVL(nom,USR_NOM) , 
                            USR_PRENOM  = NVL(prenom,USR_PRENOM),
                            USR_ADRESSE = NVL(adresse,USR_ADRESSE),
                            USR_NIP     = NVL(nip,USR_NIP)
        WHERE USR_ID = id;
    
        IF current_role = 'CLIENT' THEN
             UPDATE T_CLIENT SET CLI_TEL = NVL(telephone,CLI_TEL)
             WHERE CLI_ID =id;
            
        END IF;
    
    END;     
/
 
CREATE OR REPLACE PROCEDURE delete_user(email   IN T_USER.USR_EMAIL%TYPE ) AS
        id T_USER.USR_ID%TYPE;
        role T_USER.USR_ROLE%TYPE;
    BEGIN 
        SELECT USR_ID, USR_ROLE INTO id, role FROM T_USER WHERE USR_EMAIL = email;

        DELETE FROM T_USER WHERE USR_ID = id;

    END;

-- pour read sans se casser la tete j'utilise une view pour modifier aller chercher le code facilement       
CREATE OR REPLACE VIEW  view_utilisateur as(
    SELECT
        tu.USR_ID,       
        tu.USR_NOM,           
        tu.USR_PRENOM,         
        tu.USR_ADRESSE,        
        tu.USR_NIP,            
        tu.USR_EMAIL,
        tu.USR_ROLE, 
        tc.CLI_TEL                

    FROM T_USER tu

    LEFT JOIN T_CLIENT tc ON tu.USR_ID = tc.CLI_ID
    LEFT JOIN T_GESTIONNAIRE tg ON tu.USR_ID = tg.GEST_ID
)
/

-- SEQUENCE UTILISATEUR
CREATE SEQUENCE SEQ_USER_ID START WITH 1 INCREMENT BY 1;




--------------------------------------COMPTE---------------------------------------------
-- TABLE DES COMPTES
CREATE TABLE T_COMPTE (
    CPT_NUMERO       NUMBER(10) PRIMARY KEY     CONSTRAINT CPT_NUMERO_NN      NOT NULL,
    CPT_TYPE         VARCHAR2(20)               CONSTRAINT CPT_TYPE_NN        NOT NULL, 
    CPT_SOLDE        NUMBER(10,2)               CONSTRAINT CPT_SOLDE_NN       NOT NULL,
    CPT_DATE_OUV     DATE                       CONSTRAINT CPT_DATE_OUV_NN    NOT NULL,
    CPT_DEVISE       VARCHAR2(10),
    CPT_LIMITE       NUMBER(10,2),
    CPT_TAUX         NUMBER(5,2),
    CPT_FRAIS_TRANSACTION       NUMBER,
    CPT_TRANSACTION_RESTANTE    NUMBER,
    CLI_ID           INT,
    CONSTRAINT CK_CPT_TYPE      CHECK       (CPT_TYPE IN ('CHEQUE', 'CREDIT', 'LGNECRED','DEVISE', 'EPARGNE')),
    CONSTRAINT CPT_CLI_ID_FK    FOREIGN KEY (CLI_ID) REFERENCES T_CLIENT(CLI_ID) ON DELETE CASCADE
);

-- PROCEDURE
CREATE OR REPLACE procedure insert_compte(  typee  T_COMPTE.CPT_TYPE%TYPE,
                                            solde  T_COMPTE.CPT_SOLDE%TYPE,
                                            id     T_COMPTE.CLI_ID%TYPE,
                                            devise T_COMPTE.CPT_DEVISE%TYPE DEFAULT NULL,
                                            taux   T_COMPTE.CPT_TAUX%TYPE   DEFAULT NULL,
                                            limite T_COMPTE.CPT_LIMITE%TYPE DEFAULT NULL,
                                            frais  T_COMPTE.CPT_FRAIS_TRANSACTIONW%TYPE DEFAULT NULL,
                                            transaction_restante T_COMPTE.CPT_TRANSACTION_RESTANTE%TYPE DEFAULT NULL) AS
        numero T_COMPTE.CPT_NUMERO%TYPE;
    BEGIN
        IF typee IN( 'CREDIT', 'EPARGNE') AND limite IS NULL AND taux IS NULL THEN
            RAISE_APPLICATION_ERROR(-20001, 'Ce type de compte doit avoir une limite et un taux.');
        END IF;

         IF typee IN('LGNECRED') AND taux IS NULL THEN
            RAISE_APPLICATION_ERROR(-20001, 'Ce type de compte doit avoir une limite et un taux.');
        END IF;

        IF typee IN( 'CHEQUE') AND frais IS NULL AND transaction_restante IS NULL THEN
            RAISE_APPLICATION_ERROR(-20001, 'Ce type de compte doit avoir des frais et un nombre de transaction.');
        END IF;

        numero := SEQ_COMPTE_NUMERO.NEXTVAL;
        INSERT INTO T_COMPTE (CPT_NUMERO, CPT_TYPE, CPT_SOLDE, CPT_DEVISE, CPT_DATE_OUV, CPT_LIMITE, CPT_TAUX, CLI_ID, CPT_FRAIS_TRANSACTION, CPT_TRANSACTION_RESTANTE)
        VALUES(numero, typee, solde, NVL(devise,'CAD'),SYSDATE,NVL(limite,null), NVL(taux,null), id, NVL(frais,null), NVL(transaction_restante,null));
    END;
/

CREATE OR REPLACE procedure update_compte(numero T_COMPTE.CPT_NUMERO%TYPE,
                                          solde  T_COMPTE.CPT_SOLDE%TYPE) AS
    BEGIN
        UPDATE T_COMPTE SET CPT_SOLDE = (CPT_SOLDE + solde) WHERE CPT_NUMERO = numero;     
    END;
/


CREATE OR REPLACE procedure delete_compte(numero T_COMPTE.CPT_NUMERO%TYPE) AS
    BEGIN
        DELETE FROM T_COMPTE WHERE CPT_NUMERO = numero;   
    END;
/

-- Affiche les comptes DEV INACTIFS depuis 365 jours
CREATE OR REPLACE VIEW V_COMPTES_INACTIFS AS
SELECT CPT_NUMERO, CPT_TYPE, CPT_SOLDE, CPT_DATE_OUV
FROM T_COMPTE
WHERE CPT_TYPE = 'DEVISE' AND TRUNC(SYSDATE) - TRUNC(CPT_DATE_OUV) >= 365
/

 
-- Affiche tous les comptes par client
CREATE OR REPLACE VIEW View_COMPTES_PAR_CLIENT AS
    SELECT 
        C.CLI_ID,
        U.USR_NOM AS CLI_NOM,
        U.USR_PRENOM AS CLI_PRENOM,
        CP.CPT_NUMERO,
        CP.CPT_TYPE,
        CP.CPT_SOLDE,
        CP.CPT_FRAIS_TRANSACTION,
        CP.CPT_TRANSACTION_RESTANTE
FROM T_CLIENT C
JOIN T_USER U ON C.CLI_ID = U.USR_ID
JOIN T_COMPTE CP ON C.CLI_ID = CP.CLI_ID
/


-- SEQUENCE COMPTE
CREATE SEQUENCE SEQ_COMPTE_NUMERO START WITH 1 INCREMENT BY 1;

create table Stack_Compte_Request(
    -- GENRATED ALWAYS gere le id tous seul
    REQUEST_ID                NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    CPT_NUMERO                NUMBER(10),
    CPT_TYPE                  VARCHAR2(20),
    CPT_SOLDE                 NUMBER(10,2),
    CPT_DATE_OUV              DATE,
    CPT_DEVISE                VARCHAR2(10),
    CPT_LIMITE                NUMBER(10,2),
    CPT_TAUX                  NUMBER(5,2),
    CPT_FRAIS_TRANSACTION     NUMBER,
    CPT_TRANSACTION_RESTANTE  NUMBER,
    CLI_ID                    INT    
)





-------------------------------------TRANSACTION-------------------------------------
-- TABLE DES TRANSACTIONS
CREATE TABLE T_TRANSACTION (
    TRX_ID          NUMBER(10) CONSTRAINT TRX_ID_PK PRIMARY KEY,
    -- assurer integrite des type entre transaction et compte --
    TRX_MONTANT     NUMBER(10,2) CONSTRAINT TRX_MONTANT_NN NOT NULL,
    TRX_TYPE        VARCHAR2(50) CONSTRAINT TRX_TYPE_TRX_NN NOT NULL,
    TRX_DATE        DATE DEFAULT SYSDATE,
    CPT_NUMERO      NUMBER(10), 
    CONSTRAINT TRX_SRC_FK FOREIGN KEY(CPT_NUMERO) REFERENCES T_COMPTE(CPT_NUMERO)
);


CREATE OR REPLACE procedure buy(numero T_TRANSACTION.CPT_NUMERO%TYPE,
                                solde  T_TRANSACTION.TRX_MONTANT%TYPE) AS
    BEGIN
        update_compte(numero, solde);
        INSERT INTO T_TRANSACTION (TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, CPT_NUMERO)
        VALUES(SEQ_TRANSACTION_ID.NEXTVAL, solde, "BUY", SYSDATE, numero);     
    END;
/


CREATE OR REPLACE procedure sell(numero T_COMPTE.CPT_NUMERO%TYPE) AS
    BEGIN
        update_compte(numero, solde);
        INSERT INTO T_TRANSACTION (TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, CPT_NUMERO)
        VALUES(SEQ_TRANSACTION_ID.NEXTVAL, solde, "SELL", SYSDATE, numero);  
    END;
/

-- PROCEDURE
CREATE OR REPLACE view view_Full_Transaction as(
    SELECT
        tc.CPT_NUMERO,
        tc.CPT_TYPE,
        tt.TRX_ID,
        tt.TRX_MONTANT,
        tt.TRX_DATE,
        tt.TRX_TYPE
    FROM
        tc T_COMPTE 
    JOIN   tt T_TRANSACTION ON tt.CPT_NUMERO = tc.CPT_NUMERO
)

--SEQUENCE
CREATE SEQUENCE SEQ_TRANSACTION_ID START WITH 1 INCREMENT BY 1;





---------------------------------------TABLE VIREMENT------------------------------------
CREATE TABLE T_VIREMENT(

)

-- TABLE DES DEMANDES CLIENTS
CREATE TABLE T_DEMANDE_CLIENT (
    DMD_ID              INT CONSTRAINT DMD_ID_PK PRIMARY KEY,
    CLI_ID              INT CONSTRAINT DMD_CLI_ID_NN NOT NULL,
    DMD_TYPE            VARCHAR2(20) CONSTRAINT DMD_TYPE_NN NOT NULL,
    DMD_TYPE_CPT        VARCHAR2(20),
    DMD_CPT_CIBLE       VARCHAR2(20),
    DMD_CPT_TYPE_CIBLE  VARCHAR2(20),
    DMD_DATE            DATE CONSTRAINT DMD_DATE_NN NOT NULL,
    GEST_ID             INT,
    CONSTRAINT DMD_CLI_ID_FK FOREIGN KEY(CLI_ID) REFERENCES T_CLIENT(CLI_ID),
    CONSTRAINT DMD_GEST_ID_FK FOREIGN KEY(GEST_ID) REFERENCES T_GESTIONNAIRE(GEST_ID),
    CONSTRAINT DMD_CPT_CIBLE_FK FOREIGN KEY(DMD_CPT_CIBLE, DMD_CPT_TYPE_CIBLE) REFERENCES T_COMPTE(CPT_NUMERO, CPT_TYPE)
);


CREATE SEQUENCE SEQ_DEMANDE_ID START WITH 1 INCREMENT BY 1;


-- TRIGGERS
-- Remplit automatiquement TRX_ID lors d'une nouvelle transaction
CREATE OR REPLACE TRIGGER TRG_BI_TRANSACTION
BEFORE INSERT ON T_TRANSACTION
FOR EACH ROW
BEGIN
    SELECT SEQ_TRANSACTION_ID.NEXTVAL INTO :NEW.TRX_ID FROM dual;
END;


--Remplit automatiquement DMD_ID lors d'une nouvelle demande
CREATE OR REPLACE TRIGGER TRG_BI_DEMANDE
BEFORE INSERT ON T_DEMANDE_CLIENT
FOR EACH ROW
BEGIN
    SELECT SEQ_DEMANDE_ID.NEXTVAL INTO :NEW.DMD_ID FROM dual;
END;



-- VUES



-- Affiche les transactions avec type de compte
CREATE OR REPLACE VIEW V_TRANSACTIONS_DETAILLEES AS
SELECT T.TRX_ID, T.TRX_MONTANT, T.TRX_TYPE, T.TRX_DATE,
       T.TRX_SRC_NUM, T.TRX_DEST_NUM, 
       CS.CPT_TYPE AS TYPE_SOURCE, CD.CPT_TYPE AS TYPE_DESTINATION
FROM T_TRANSACTION T
LEFT JOIN T_COMPTE CS ON T.TRX_SRC_NUM = CS.CPT_NUMERO AND T.TRX_SRC_TYPE = CS.CPT_TYPE
LEFT JOIN T_COMPTE CD ON T.TRX_DEST_NUM = CD.CPT_NUMERO AND T.TRX_DEST_TYPE = CD.CPT_TYPE;


-- Affiche les demandes clients non traitees
CREATE OR REPLACE VIEW V_DEMANDES_NON_TRAITEES AS
SELECT D.DMD_ID, D.CLI_ID, U.USR_NOM AS CLI_NOM, U.USR_PRENOM AS CLI_PRENOM,
       D.DMD_TYPE, D.DMD_TYPE_CPT, D.DMD_CPT_CIBLE, D.DMD_DATE
FROM T_DEMANDE_CLIENT D
JOIN T_CLIENT C ON D.CLI_ID = C.CLI_ID
JOIN T_USER U ON C.USR_ID = U.USR_ID
WHERE D.GEST_ID IS NULL;







--------------------------DML pour users--------------------------------
-- testing / inserting users

-- insere un compte cheque a la creation du cote db
EXEC insert_user('William', 'Descoteaux', '555 rue des sprinkt', 'wiwi@gmail.com', '12345','CLIENT', '111-111-1111');
EXEC insert_user('Tafsirul', 'Huque', '555 rue des sprinkt', 'tafi@gmail.com', '12345','CLIENT', '222-222-2222');
EXEC insert_user('Lois', 'Michelin', '555 rue des sprinkt', 'lois@gmail.com', '12345', 'GESTIONNAIRE', '333-333-3333');
EXEC insert_user('Sid', 'Ahmed', '555 rue des sprinkt', 'sid@gmail.com', '12345', 'GESTIONNAIRE', '444-444-4444');
COMMIT;

-- --fonctionne
-- EXEC update_user('Goonie', 'Descoteaux', '555 rue des sprinkt', 'wiwi@gmail.com', '12345', '111-111-1111');
-- COMMIT;
-- EXEC delete_user('wiwi@gmail.com');
-- EXEC delete_user('tafi@gmail.com');
-- EXEC delete_user('lois@gmail.com');
-- EXEC delete_user('sid@gmail.com');
-- COMMIT;
--------------------------DML pour Compte--------------------------------


--------------------------DML pour transaction--------------------------------

-- T_TRANSACTION
INSERT INTO T_TRANSACTION (TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, TRX_SRC_NUM, TRX_SRC_TYPE, TRX_DEST_NUM, TRX_DEST_TYPE)
VALUES (SEQ_TRANSACTION_ID.NEXTVAL, 200.00, 'VIREMENT', SYSDATE, 'CHQ-001', 'CHEQUE', 'CHQ-002', 'CHEQUE');

INSERT INTO T_TRANSACTION (TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, TRX_SRC_NUM, TRX_SRC_TYPE, TRX_DEST_NUM, TRX_DEST_TYPE)
VALUES (SEQ_TRANSACTION_ID.NEXTVAL, 300.00, 'DEPOT', SYSDATE, NULL, NULL, 'EPR-001', 'EPARGNE');

INSERT INTO T_TRANSACTION (TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, TRX_SRC_NUM, TRX_SRC_TYPE, TRX_DEST_NUM, TRX_DEST_TYPE)
VALUES (SEQ_TRANSACTION_ID.NEXTVAL, 150.00, 'RETRAIT', SYSDATE, 'CHQ-001', 'CHEQUE', NULL, NULL);

INSERT INTO T_TRANSACTION (TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, TRX_SRC_NUM, TRX_SRC_TYPE, TRX_DEST_NUM, TRX_DEST_TYPE)
VALUES (SEQ_TRANSACTION_ID.NEXTVAL, 500.00, 'VIREMENT', SYSDATE, 'CHQ-002', 'CHEQUE', 'CRD-002', 'CREDIT');




-- T_DEMANDE_CLIENT

-- Ajout de compte
INSERT INTO T_DEMANDE_CLIENT (DMD_ID, CLI_ID, DMD_TYPE, DMD_TYPE_CPT, DMD_DATE, GEST_ID)
VALUES (SEQ_DEMANDE_ID.NEXTVAL, 1, 'AJOUT', 'DEVISE', SYSDATE, NULL);

-- Fermeture compte
INSERT INTO T_DEMANDE_CLIENT (DMD_ID, CLI_ID, DMD_TYPE, DMD_CPT_CIBLE, DMD_CPT_TYPE_CIBLE, DMD_DATE, GEST_ID)
VALUES (SEQ_DEMANDE_ID.NEXTVAL, 1, 'FERMETURE', 'EPR-001', 'EPARGNE', SYSDATE, NULL);

-- Fermeture totale
INSERT INTO T_DEMANDE_CLIENT (DMD_ID, CLI_ID, DMD_TYPE, DMD_DATE, GEST_ID)
VALUES (SEQ_DEMANDE_ID.NEXTVAL, 2, 'FERMETURE_TOTALE', SYSDATE, NULL);

-- Ajout avec gestionnaire affecte
INSERT INTO T_DEMANDE_CLIENT (DMD_ID, CLI_ID, DMD_TYPE, DMD_TYPE_CPT, DMD_DATE, GEST_ID)
VALUES (SEQ_DEMANDE_ID.NEXTVAL, 2, 'AJOUT', 'CREDIT', SYSDATE, 1);
