-- pour read sans se casser la tete j'utilise une view pour modifier aller chercher le code facilement       
CREATE OR REPLACE VIEW  view_utilisateur as(
    SELECT
        tu.USR_ID,       
        tu.USR_NOM,           
        tu.USR_PRENOM,         
        tu.USR_ADRESSE,        
        tu.USR_NIP,            
        tu.USR_EMAIL,
        tu.USR_ROLE, 
        tc.CLI_TEL                

    FROM T_USER tu

    LEFT JOIN T_CLIENT tc ON tu.USR_ID = tc.CLI_ID
    LEFT JOIN T_GESTIONNAIRE tg ON tu.USR_ID = tg.GEST_ID
)

-- ========================================
-- view:  view_utilisateur

-- Purpose: vue complete d'un utilisateur
-- Auteur: William Descoteaux
-- ========================================