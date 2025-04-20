-- tables
@ ./tables/user.sql;
@ ./tables/compte.sql;
@ ./tables/transaction.sql;
@ ./tables/queue_compte_request.sql;
@ ./tables/queue_virement.sql;

--sequence
@ ./sequence/sequence.sql;

--view
@ ./views/compte.sql;
@ ./views/transaction.sql;
@ ./views/user.sql;

--packages
@ ./packages/compteManager/body.sql;
@ ./packages/compteManager/spec.sql;
@ ./packages/transactionManager/body.sql;
@ ./packages/transactionManager/spec.sql;
@ ./packages/userManager/body.sql;
@ ./packages/userManager/spec.sql;


--insert samples
@ ./data/insert_samples.sql;