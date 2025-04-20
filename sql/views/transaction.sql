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
