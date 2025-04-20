---------------------------------------TABLE VIREMENT------------------------------------
CREATE TABLE QUEUE_VIREMENT(
     -- row de virement
    VIR_ID          NUMBER PRIMARY KEY,
    VIR_SRC         NUMBER NOT NULL,
    VIR_DES         NUMBER NOT NULL,
    VIR_PSW         VARCHAR2(50) NOT NULL,
    -- row de transaction
    TRX_MONTANT     NUMBER(10,2)  NOT NULL,
    TRX_TYPE        VARCHAR2(50)  NOT NULL,
    TRX_DATE        DATE DEFAULT SYSDATE,
    CONSTRAINT VIR_SRC_FK FOREIGN KEY(VIR_SRC) REFERENCES T_COMPTE(CPT_NUMERO),
    CONSTRAINT VIR_DES_FK FOREIGN KEY(VIR_DES) REFERENCES T_COMPTE(CPT_NUMERO),
    CONSTRAINT VIR_TRX_TYPE_CK CHECK (TRX_TYPE IN('DEPOT' , 'RETRAIT'))
)
-- ========================================
-- table:   QUEUE_VIREMENT
--
-- Purpose: table des virement confirmer
-- Auteur:  William Descoteaux
-- ========================================