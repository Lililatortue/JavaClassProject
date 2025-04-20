create table Queue_Compte_Request(
    CPT_NUMERO                NUMBER(10),      
    CPT_TYPE                  VARCHAR2(20),
    CPT_SOLDE                 NUMBER(10,2),
    CPT_DATE_OUV              DATE,    
    CLI_ID                    NUMBER,  
    CPT_DEVISE                VARCHAR2(10)      DEFAULT NULL,
    CPT_LIMITE                NUMBER(10,2)      DEFAULT NULL,
    CPT_TAUX                  NUMBER(5,2)       DEFAULT NULL,
    CPT_FRAIS_TRANSACTION     NUMBER            DEFAULT NULL,
    CPT_TRANSACTION_RESTANTE  NUMBER            DEFAULT NULL, 
    PRIMARY KEY (CLI_ID,CPT_TYPE),
    CONSTRAINT FK_CLI_ID   FOREIGN KEY (CLI_ID) REFERENCES T_CLIENT(CLI_ID)
)

-- ========================================
-- table:  Queue_Compte_Request
--            
-- Purpose: table des comptes qui gere les demande de requete
-- Auteur: William Descoteaux
-- ========================================

