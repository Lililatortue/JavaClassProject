package com.buisness.compte;

import java.time.LocalDate;

import com.buisness.client.Client;

public class LigneDeCredit extends CompteInteret {
	//variable
		private static final long serialVersionUID = -3662972914243024057L;
	
	//constructeur
		public LigneDeCredit(Client clientId,  double tauxInteret) {
			super(clientId, CompteType.LGNCRED , tauxInteret, LocalDate.now());
			this.interetMensuelDu=0.0;
		}
		
		protected LigneDeCredit(Client clientId,  double tauxInteret, LocalDate localDate) {
			super(clientId, CompteType.LGNCRED , tauxInteret, localDate);
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
		
	//toString implementation
		@Override
		public String toString() {
			return "Client id: "+this.getClientId() +"      Requete: "+this.type+"      taux interet: "+this.getTauxInteretAnnuel();
		}

		
}
