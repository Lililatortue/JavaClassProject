-- TABLE DES TRANSACTIONS
CREATE TABLE T_TRANSACTION (
    TRX_ID          NUMBER(10) CONSTRAINT TRX_ID_PK PRIMARY KEY,
    -- assurer integrite des type entre transaction et compte --
    TRX_MONTANT     NUMBER(10,2) CONSTRAINT TRX_MONTANT_NN NOT NULL,
    TRX_TYPE        VARCHAR2(50) CONSTRAINT TRX_TYPE_TRX_NN NOT NULL ,
    TRX_DATE        DATE DEFAULT SYSDATE,
    CPT_NUMERO      NUMBER(10), 
    CONSTRAINT TRX_SRC_FK FOREIGN KEY(CPT_NUMERO) REFERENCES T_COMPTE(CPT_NUMERO),
    CONSTRAINT TRX_TYPE_CK CHECK (TRX_TYPE IN('DEPOT' , 'RETRAIT'))
   
);

-- ========================================
-- table:   T_TRANSACTION
--
-- Purpose: table des transactions confirmer
-- Auteur:  Tafsirul Huque
-- ========================================

SELECT * FROM T_TRANSACTION ;

DROP TABLE T_TRANSACTION;