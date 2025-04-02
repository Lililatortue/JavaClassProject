package com.buisness.compte;

import com.buisness.client.Client;

public class CompteDevise extends Compte {

	//variable
		private static final long serialVersionUID = 826859753794501933L;
		private Devise devise;
	
	//constructeur
		public CompteDevise(Client clientId, double solde,Devise devise) {
			super(clientId, solde, CompteType.DEV );
			this.devise = devise;
		}

		@Override
		protected void deposer(double montant) {
			this.solde +=montant * devise.exchangeRate;
		
		}

		@Override
		protected double retirer(double montant) throws Exception {
			this.solde -=montant ;
			return montant / devise.exchangeRate;
		}
	//toString implementation
		@Override
		public String toString() {
			return "Client id: "+this.getClientId() +"      Requete: "+this.type+"      devise: "+this.devise;
		}

}

