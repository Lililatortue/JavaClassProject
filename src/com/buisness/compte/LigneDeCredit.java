package com.buisness.compte;

import java.time.LocalDate;

public class LigneDeCredit extends CompteInteret {
	/**
	 * 
	 */
		private static final long serialVersionUID = -3662972914243024057L;
	//variable
	//constructeur
		public LigneDeCredit(double tauxInteret) {
			super("LGNCRED", tauxInteret, LocalDate.now());
			this.interetMensuelDu=0.0;
		}
		
		protected LigneDeCredit(String n, double ti, LocalDate ld) {
			super(n, ti, ld);
			update();
		}
		
	//fonction public 
		public double emprunter(double montant) throws Exception {
				return this.retirer(montant);
		}
	// CompteDeCredit implimentation
		@Override
		protected void setInteretMensuelDu() {
			this.interetMensuelDu+=(this.getTauxInteretAnnuel() / 12) * this.solde;
		}
	//IInterestEvent
		@Override
		public void update() {
			if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
				setInteretMensuelDu();
		}
	//state implementation
		@Override
		public String toString() {
			return super.toString();
		}
}
