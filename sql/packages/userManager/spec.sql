CREATE OR REPLACE PACKAGE pkg_userManager AS
-- ========================================
-- Procedure: proc_insertUser
--            proc_updateUser
--            proc_deleteUser
--
-- Purpose: manipulation des tables du scripts user
-- Auteur: William
-- ========================================

    PROCEDURE proc_insertUser(
        nom           IN T_USER.USR_NOM%TYPE,
        prenom        IN T_USER.USR_PRENOM%TYPE,
        adresse       IN T_USER.USR_ADRESSE%TYPE,
        email         IN T_USER.USR_EMAIL%TYPE,
        nip           IN T_USER.USR_NIP%TYPE,
        current_role  IN T_USER.USR_ROLE%TYPE,
        telephone     IN T_CLIENT.CLI_TEL%TYPE DEFAULT NULL
    );


    PROCEDURE proc_updateUser(
        nom         IN T_USER.USR_NOM%TYPE,
        prenom      IN T_USER.USR_PRENOM%TYPE,
        adresse     IN T_USER.USR_ADRESSE%TYPE,
        email       IN T_USER.USR_EMAIL%TYPE,
        nip         IN T_USER.USR_NIP%TYPE,
        telephone   IN T_CLIENT.CLI_TEL%TYPE DEFAULT NULL
    );


    PROCEDURE proc_deleteUser(
        email       IN T_USER.USR_EMAIL%TYPE
    );
END pkg_userManager;
/