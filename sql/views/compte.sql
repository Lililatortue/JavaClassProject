-- Affiche les comptes DEV INACTIFS depuis 365 jours
CREATE OR REPLACE VIEW V_COMPTES_INACTIFS AS
SELECT CPT_NUMERO, CPT_TYPE, CPT_SOLDE, CPT_DATE_OUV
FROM T_COMPTE
WHERE CPT_TYPE = 'DEVISE' AND TRUNC(SYSDATE) - TRUNC(CPT_DATE_OUV) >= 365


 
-- Affiche tous les comptes par client
CREATE OR REPLACE VIEW View_COMPTES_PAR_CLIENT AS
    SELECT 
        C.CLI_ID,
        U.USR_NOM AS CLI_NOM,
        U.USR_PRENOM AS CLI_PRENOM,
        CP.CPT_NUMERO,
        CP.CPT_TYPE,
        CP.CPT_SOLDE,
        CP.CPT_FRAIS_TRANSACTION,
        CP.CPT_TRANSACTION_RESTANTE
FROM T_CLIENT C
JOIN T_USER U ON C.CLI_ID = U.USR_ID
JOIN T_COMPTE CP ON C.CLI_ID = CP.CLI_ID
/

-- ========================================
-- view:  View_COMPTES_INACTIFS
--        View_COMPTES_PAR_CLIENT
--
-- Purpose: vue complete d'un utilisateur
-- Auteur: Tafsirul Huque
-- ========================================