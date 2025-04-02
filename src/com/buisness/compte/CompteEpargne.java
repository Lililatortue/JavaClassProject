package com.buisness.compte;

import java.time.LocalDate;

import com.buisness.client.Client;



/*
 * 0.005% de taux interet mensuelle
 * 
 * 
 * */
public class CompteEpargne extends CompteInteret  {
	//variable
		private static final long serialVersionUID = -8110410012131872667L;
		private double limite;
		
	//constructeur
		public CompteEpargne(Client clientId, double tauxInteret, double limite) {
			super(clientId, CompteType.EPRGN , tauxInteret, LocalDate.now());
			this.limite = limite;
		}
		protected CompteEpargne(Client clientId, double tauxInteret, LocalDate date, double limite) {
			super(clientId, CompteType.EPRGN , tauxInteret, date);
	
			this.limite = limite;
			this.update();
		}
	//Compte implementation
		@Override
		public void deposer(double montant) {
			if(montant>0)
				this.solde+=montant;
			//else
				//throw new Exception("doit deposer plus que 0.");
		}
		

		@Override
		protected double retirer(double montant) throws Exception  {
			if(montant > 0 && this.solde - montant > 0 && this.limite-montant > 0) {
				this.limite -= montant;
				this.solde -= montant;
				return montant;	
			} else {
				throw new Exception("doit deposer plus que 0.");}
		}
	//IInterestEvent
		@Override
		public void update() {
			if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
				setInteretMensuelDu();
		}
	//CompteDeCredit implementation
		@Override
		protected void setInteretMensuelDu() {
			this.solde+=(this.getTauxInteretAnnuel() / 12) * this.solde;
		}
	//state implementation
		@Override
		public String toString() {
			return "Client id: "+this.getClientId() +"      Requete: "+this.type+"      taux interet: "+this.getTauxInteretAnnuel();
		}
	
}
