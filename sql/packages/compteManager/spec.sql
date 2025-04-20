create or replace PACKAGE pkg_compteManager AS

-- ========================================
-- Procedure: private proc_insertCompte
--            proc_deleteCompte
--
-- Purpose: manipulation rajoute et enlevement de la table compte
-- Auteur: William
-- ========================================
-- proc_insertCompte 'toute insertion devrait etre accepter paraccount request et non directement tolerer';
    PROCEDURE proc_insertCompte(
        typee                IN  T_COMPTE.CPT_TYPE%TYPE,
        solde                IN  T_COMPTE.CPT_SOLDE%TYPE,
        id                   IN  T_COMPTE.CLI_ID%TYPE,
        devise               IN  T_COMPTE.CPT_DEVISE%TYPE                         DEFAULT NULL,
        taux                 IN  T_COMPTE.CPT_TAUX%TYPE                           DEFAULT NULL,
        limite               IN  T_COMPTE.CPT_LIMITE%TYPE                         DEFAULT NULL,
        frais                IN  T_COMPTE.CPT_FRAIS_TRANSACTION%TYPE              DEFAULT NULL,
        transaction_gratuite_restante  IN  T_COMPTE.CPT_TRANSACTION_RESTANTE%TYPE DEFAULT NULL
    );


    PROCEDURE proc_deleteCompte(
        numero IN T_COMPTE.CPT_NUMERO%TYPE
    );

-- ========================================
-- Procedure: private proc_createAccountRequest
--            proc_acceptAccountRequest
--            proc_DenyAccountRequest
--
-- Purpose: manipulation rajoute et enlevement de la table Queue_Account_Request
-- Auteur: William
-- ========================================
    PROCEDURE proc_createAccountRequest(
        request_id               IN  Queue_Compte_Request.REQUEST_ID%TYPE,
        numero                   IN  Queue_Compte_Request.CPT_NUMERO%TYPE,  
        compte_type              IN  Queue_Compte_Request.CPT_TYPE%TYPE,
        CLI_ID                   IN  Queue_Compte_Request.CLI_ID%TYPE,                        
        CPT_DEVISE               IN  Queue_Compte_Request.CPT_DEVISE%TYPE                DEFAULT NULL,                
        CPT_LIMITE               IN  Queue_Compte_Request.CPT_LIMITE%TYPE                DEFAULT NULL,               
        CPT_TAUX                 IN Queue_Compte_Request.CPT_TAUX%TYPE                   DEFAULT NULL,               
        CPT_FRAIS_TRANSACTION    IN Queue_Compte_Request.CPT_FRAIS_TRANSACTION%TYPE      DEFAULT NULL,  
        CPT_TRANSACTION_RESTANTE IN  Queue_Compte_Request.CPT_TRANSACTION_RESTANTE%TYPE  DEFAULT NULL 
    );

    PROCEDURE proc_acceptAccountRequest(
        request_id               IN  Queue_Compte_Request.REQUEST_ID%TYPE
    );

    PROCEDURE proc_DenyAccountRequest(
        request_id               IN  Queue_Compte_Request.REQUEST_ID%TYPE
    );
END pkg_compteManager;