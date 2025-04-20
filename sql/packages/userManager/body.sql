CREATE OR REPLACE PACKAGE BODY pkg_userManager AS

-- procedure pour inserer un utilisateur dans la bd
    PROCEDURE proc_insertUser(  nom         IN T_USER.USR_NOM%TYPE,
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


-- procedure pour update un utilisateur dans la bd
    PROCEDURE proc_updateUser(  nom         IN T_USER.USR_NOM%TYPE,
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

-- procedure pour delete un utilisateur dans la bd
    PROCEDURE proc_deleteUser(email   IN T_USER.USR_EMAIL%TYPE ) AS
        id T_USER.USR_ID%TYPE;
        role T_USER.USR_ROLE%TYPE;
    BEGIN 
        SELECT USR_ID, USR_ROLE INTO id, role FROM T_USER WHERE USR_EMAIL = email;

        DELETE FROM T_USER WHERE USR_ID = id;

    END;
END pkg_userManager;

-- ========================================
-- Procedure: proc_insertUser
--            proc_updateUser
--            proc_deleteUser
--
-- Purpose: manipulation des tables du scripts user
-- Auteur: William
-- ========================================

