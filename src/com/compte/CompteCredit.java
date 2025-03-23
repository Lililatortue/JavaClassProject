package com.compte;

import java.time.LocalDate;

/*
 * Compte Credit 
 * limite->
 * retirer est un emprunt
 * 
 * */
public class CompteCredit extends CompteDeCredit {
	
	private double limite;	
	//constructeur
		//creation du compte
		public CompteCredit(String n,  double ti, double l){
			super(n, ti, LocalDate.now());
			this.setLimite(l);
			this.interetMensuelDu = 0.0;
		}
		
		protected CompteCredit(String n, double ti, LocalDate ld, double l, double m){
			super(n, ti, ld);
			this.setLimite(l);
			this.update();
		}
	
	//fonction public 
		public double emprunter(double montant) throws Exception {
			if(this.solde + montant > this.limite)
				return this.retirer(montant);
			else
				throw new Exception("ne peux emprunter de l'argent l'imite atteinte");//devoir creer exception pour les gerer
		}
	//CompteDeCredit implementation 
		@Override
		protected void setInteretMensuelDu() {
			this.interetMensuelDu+=(this.getTauxInteretAnnuel() / 12) * this.solde;
		}
		
	//IInterestEvent implementation
		@Override
		public void update() {
			if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
				setInteretMensuelDu();
		}
		//setters
		private void setLimite(Double l) {
			this.limite=l;
		}

		
		
		
}
