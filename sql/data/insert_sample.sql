-- insere un compte cheque a la creation du cote db
EXEC pkg_userManager.proc_insertUser('William', 'Descoteaux', '555 rue des sprinkt', 'wiwi@gmail.com', '12345','CLIENT', '111-111-1111');
EXEC pkg_userManager.proc_insertUser('Tafsirul', 'Huque', '555 rue des sprinkt', 'tafi@gmail.com', '12345','CLIENT', '222-222-2222');
EXEC pkg_userManager.proc_insertUser('Lois', 'Michelin', '555 rue des sprinkt', 'lois@gmail.com', '12345', 'GESTIONNAIRE');
EXEC pkg_userManager.proc_insertUser('Sid', 'Ahmed', '555 rue des sprinkt', 'sid@gmail.com', '12345', 'GESTIONNAIRE');
COMMIT;


EXEC pkg_userManager.proc_updateUser('Goonie', 'Descoteaux', '555 rue des sprinkt', 'wiwi@gmail.com', '12345', '111-111-1111');
COMMIT;


 EXEC pkg_userManager.proc_deleteUser('wiwi@gmail.com');
 EXEC pkg_userManager.proc_deleteUser('tafi@gmail.com');
 EXEC pkg_userManager.proc_deleteUser('lois@gmail.com');
 EXEC pkg_userManager.proc_deleteUser('sid@gmail.com');
 COMMIT;


--------------------------DML pour Compte--------------------------------
EXEC pkg_compteManager.proc_insertCompte();
EXEC pkg_compteManager.proc_insertCompte();
EXEC pkg_compteManager.proc_insertCompte();
EXEC pkg_compteManager.proc_insertCompte();
COMMIT;


EXEC pkg_compteManager.proc_deleteCompte();
COMMIT;