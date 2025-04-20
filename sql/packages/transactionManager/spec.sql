CREATE OR REPLACE PACKAGE pkg_transactionManager AS
    PROCEDURE proc_buyTransaction(
        numero IN T_TRANSACTION.CPT_NUMERO%TYPE,
        solde  IN T_TRANSACTION.TRX_MONTANT%TYPE
    );

    PROCEDURE proc_sellTransaction(
        numero IN T_TRANSACTION.CPT_NUMERO%TYPE,
        solde  IN T_TRANSACTION.TRX_MONTANT%TYPE
    );
END pkg_transactionManager;