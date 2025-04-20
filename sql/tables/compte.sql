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


COMMENT ON TABLE T_COMPTE IS 'une table pour enregistrer les donne pour aller reelement chercher linformation mieux representer des tables ont ete creer';

-- ========================================
-- table:  T_COMPTE
--            
-- Purpose: table des comptes
-- Auteur: Tafsirul Huque
-- ========================================

drop table T_COMPTE;