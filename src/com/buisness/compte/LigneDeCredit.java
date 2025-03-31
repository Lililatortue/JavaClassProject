package com.buisness.compte;

import java.time.LocalDate;

import com.buisness.client.Client;

public class LigneDeCredit extends CompteInteret {
	/**
	 * 
	 */
		private static final long serialVersionUID = -3662972914243024057L;
	//variable
	//constructeur
		public LigneDeCredit(Client clientId,double tauxInteret) {
			super(clientId, tauxInteret, LocalDate.now());
			this.interetMensuelDu=0.0;
		}
		
		protected LigneDeCredit(Client clientId, double tauxInteret, LocalDate localDate) {
			super(clientId, tauxInteret, localDate);
			update();
		}
		
		@Override
		public void setCompteId(Client clientId) {
			this.compteId = "LGNCRED"+clientId.getId();
			
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
