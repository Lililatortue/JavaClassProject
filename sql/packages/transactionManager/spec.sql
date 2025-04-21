CREATE OR REPLACE PACKAGE pkg_transactionManager AS
-- ========================================
-- Procedure: private proc_updateSoldeCompte
--            proc_buyTransaction
--            proc_sellTransaction
--
-- Purpose: manipulation des tables du scripts transaction 
-- Auteur: William
-- ========================================
    PROCEDURE proc_buyTransaction(
        numero IN T_TRANSACTION.CPT_NUMERO%TYPE,
        solde  IN T_TRANSACTION.TRX_MONTANT%TYPE
    );

    PROCEDURE proc_sellTransaction(
        numero IN T_TRANSACTION.CPT_NUMERO%TYPE,
        solde  IN T_TRANSACTION.TRX_MONTANT%TYPE
    );
-- ========================================
-- Procedure: proc_createvirement
--            proc_acceptVirement
--            proc_deleteVirement
--
-- Purpose: manipulation des tables de virement
-- Auteur: William
-- ========================================
    PROCEDURE proc_createvirement(
        source          IN QUEUE_VIREMENT.VIR_SRC%TYPE,
        destination     IN QUEUE_VIREMENT.VIR_DES%TYPE,
        psw             IN QUEUE_VIREMENT.VIR_PSW%TYPE,
        montant         IN QUEUE_VIREMENT.TRX_MONTANT%TYPE,
        compte          IN QUEUE_VIREMENT.CPT_NUMERO%TYPE
    );

    PROCEDURE proc_acceptVirement(
        id         IN QUEUE_VIREMENT.VIR_ID%TYPE
    );

    PROCEDURE proc_deleteVirement(
        id         IN QUEUE_VIREMENT.VIR_ID%TYPE
    );
END pkg_transactionManager;