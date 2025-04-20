CREATE OR REPLACE PACKAGE pkg_userManager AS
    -- Inserts a new user into the system (client or manager)
    PROCEDURE proc_insertUser(
        nom           IN T_USER.USR_NOM%TYPE,
        prenom        IN T_USER.USR_PRENOM%TYPE,
        adresse       IN T_USER.USR_ADRESSE%TYPE,
        email         IN T_USER.USR_EMAIL%TYPE,
        nip           IN T_USER.USR_NIP%TYPE,
        current_role  IN T_USER.USR_ROLE%TYPE,
        telephone     IN T_CLIENT.CLI_TEL%TYPE DEFAULT NULL
    );

    -- Updates an existing user by email (partial updates supported)
    PROCEDURE proc_updateUser(
        nom         IN T_USER.USR_NOM%TYPE,
        prenom      IN T_USER.USR_PRENOM%TYPE,
        adresse     IN T_USER.USR_ADRESSE%TYPE,
        email       IN T_USER.USR_EMAIL%TYPE,
        nip         IN T_USER.USR_NIP%TYPE,
        telephone   IN T_CLIENT.CLI_TEL%TYPE DEFAULT NULL
    );

    -- Deletes a user by email
    PROCEDURE proc_deleteUser(
        email       IN T_USER.USR_EMAIL%TYPE
    );
END pkg_userManager;
/