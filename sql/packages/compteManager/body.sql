CREATE OR REPLACE PACKAGE BODY pkg_compteManager AS

    PROCEDURE proc_insertCompte(    typee  T_COMPTE.CPT_TYPE%TYPE,
                                    solde  T_COMPTE.CPT_SOLDE%TYPE,
                                    id     T_COMPTE.CLI_ID%TYPE,
                                    devise T_COMPTE.CPT_DEVISE%TYPE DEFAULT NULL,
                                    taux   T_COMPTE.CPT_TAUX%TYPE   DEFAULT NULL,
                                    limite T_COMPTE.CPT_LIMITE%TYPE DEFAULT NULL,
                                    frais  T_COMPTE.CPT_FRAIS_TRANSACTION%TYPE DEFAULT NULL,
                                    transaction_gratuite_restante T_COMPTE.CPT_TRANSACTION_RESTANTE%TYPE DEFAULT NULL) AS
        numero T_COMPTE.CPT_NUMERO%TYPE;
    BEGIN
        IF typee IN( 'CREDIT', 'EPARGNE') AND limite IS NULL AND taux IS NULL THEN
            RAISE_APPLICATION_ERROR(-20001, 'Ce type de compte doit avoir une limite et un taux.');
        END IF;

         IF typee IN('LGNECRED') AND taux IS NULL THEN
            RAISE_APPLICATION_ERROR(-20001, 'Ce type de compte doit avoir une limite et un taux.');
        END IF;

        IF typee IN( 'CHEQUE') AND frais IS NULL AND transaction_gratuite_restante IS NULL THEN
            RAISE_APPLICATION_ERROR(-20001, 'Ce type de compte doit avoir des frais et un nombre de transaction.');
        END IF;

        numero := SEQ_COMPTE_NUMERO.NEXTVAL;
        INSERT INTO T_COMPTE (CPT_NUMERO, CPT_TYPE, CPT_SOLDE, CPT_DEVISE, CPT_DATE_OUV, CPT_LIMITE, CPT_TAUX, CLI_ID, CPT_FRAIS_TRANSACTION, CPT_TRANSACTION_RESTANTE)
        VALUES(numero, typee, solde, NVL(devise,'CAD'),SYSDATE,NVL(limite,null), NVL(taux,null), id, NVL(frais,null), NVL(transaction_gratuite_restante,null));
    END;


    PROCEDURE proc_deleteCompte(numero  T_COMPTE.CPT_NUMERO%TYPE) IS
    BEGIN
        DELETE FROM T_COMPTE WHERE CPT_NUMERO = numero;
    END;

END pkg_compteManager;

