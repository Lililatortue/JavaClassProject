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
            SEQ_TRANSACTION_ID.NEXTVAL, solde, 'DEPOT', SYSDATE, numero
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
            SEQ_TRANSACTION_ID.NEXTVAL, -solde, 'RETRAIT', SYSDATE, numero
        );
    END;
  

    PROCEDURE proc_createvirement(
        source          IN QUEUE_VIREMENT.VIR_SRC%TYPE,
        destination     IN QUEUE_VIREMENT.VIR_DES%TYPE,
        psw             IN QUEUE_VIREMENT.VIR_PSW%TYPE,
        montant         IN QUEUE_VIREMENT.TRX_MONTANT%TYPE,
        compte          IN QUEUE_VIREMENT.CPT_NUMERO%TYPE
    ) AS
    id QUEUE_VIREMENT.VIR_ID%TYPE;
    BEGIN
        id := SEQ_VIREMENT_ID.NEXTVAL;
        INSERT INTO QUEUE_VIREMENT (VIR_ID, VIR_SRC, VIR_DES, VIR_PSW, TRX_MONTANT, TRX_DATE, CPT_NUMERO)
        VALUES (id, source, destination, psw, montant, SYSDATE,compte);
    END;


    PROCEDURE proc_acceptVirement(
        id         IN QUEUE_VIREMENT.VIR_ID%TYPE
    ) AS
    virement QUEUE_VIREMENT%ROWTYPE;
    BEGIN
        SELECT * INTO virement FROM QUEUE_VIREMENT WHERE VIR_ID = id;
        proc_sellTransaction(virement.VIR_SRC, virement.TRX_MONTANT);
        proc_buyTransaction(virement.VIR_DES,virement.TRX_MONTANT);
        proc_deleteVirement(id);
        EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK;
                RAISE;
    END;


    PROCEDURE proc_deleteVirement(
        id         IN QUEUE_VIREMENT.VIR_ID%TYPE
    ) AS
    BEGIN
        DELETE FROM QUEUE_VIREMENT WHERE VIR_ID = id;
    END;
END pkg_transactionManager;

SELECT * FROM QUEUE_VIREMENT;