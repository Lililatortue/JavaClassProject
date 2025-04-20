CREATE OR REPLACE PACKAGE BODY pkg_transactionManager AS

    -- seulement accessible du package
    PROCEDURE proc_updateSoldeCompte(
        numero IN T_COMPTE.CPT_NUMERO%TYPE,
        solde  IN T_COMPTE.CPT_SOLDE%TYPE
    ) AS
    BEGIN
        UPDATE T_COMPTE
        SET CPT_SOLDE = CPT_SOLDE + solde
        WHERE CPT_NUMERO = numero;
    END;

    -- procedure public
    PROCEDURE proc_buyTransaction(
        numero IN T_TRANSACTION.CPT_NUMERO%TYPE,
        solde  IN T_TRANSACTION.TRX_MONTANT%TYPE
    ) AS
    BEGIN
        proc_updateSoldeCompte(numero, solde);
        INSERT INTO T_TRANSACTION (
            TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, CPT_NUMERO
        ) VALUES (
            SEQ_TRANSACTION_ID.NEXTVAL, solde, 'BUY', SYSDATE, numero
        );
    END;

    -- procedure public
    PROCEDURE proc_sellTransaction(
        numero IN T_TRANSACTION.CPT_NUMERO%TYPE,
        solde  IN T_TRANSACTION.TRX_MONTANT%TYPE
    ) AS
    BEGIN
        proc_updateSoldeCompte(numero, -solde);
        INSERT INTO T_TRANSACTION (
            TRX_ID, TRX_MONTANT, TRX_TYPE, TRX_DATE, CPT_NUMERO
        ) VALUES (
            SEQ_TRANSACTION_ID.NEXTVAL, -solde, 'SELL', SYSDATE, numero
        );
    END;
END pkg_transactionManager;

-- ========================================
-- Procedure: private proc_updateSoldeCompte
--            proc_deleteCompte
--
-- Purpose: manipulation des tables du scripts transaction et compte
-- Auteur: William
-- ========================================