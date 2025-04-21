---------------------------------------TABLE VIREMENT------------------------------------
CREATE TABLE QUEUE_VIREMENT(
     -- row de virement
    VIR_ID          NUMBER PRIMARY KEY,
    VIR_SRC         NUMBER NOT NULL,
    VIR_DES         NUMBER NOT NULL,
    VIR_PSW         VARCHAR2(50) NOT NULL,
    -- row de transaction
    TRX_MONTANT     NUMBER(10,2)  NOT NULL,
    TRX_DATE        DATE DEFAULT SYSDATE,
    CPT_NUMERO      NUMBER(10), 
    CONSTRAINT VIR_SRC_FK FOREIGN KEY(VIR_SRC) REFERENCES T_CLIENT(CLI_ID),
    CONSTRAINT VIR_DES_FK FOREIGN KEY(VIR_DES) REFERENCES T_CLIENT(CLI_ID),
    CONSTRAINT VIR_NUMERO_FK FOREIGN KEY(CPT_NUMERO) REFERENCES T_COMPTE(CPT_NUMERO)
)
-- ========================================
-- table:   QUEUE_VIREMENT
--
-- Purpose: table des virement confirmer
-- Auteur:  William Descoteaux
-- ========================================
SELECT * FROM QUEUE_VIREMENT;