package com.buisness.compte;

import java.time.LocalDate;

/*
 * Compte Credit 
 * limite->
 * retirer est un emprunt
 * 
 * */
public class CompteCredit extends CompteInteret {
	
	/**
	 * 
	 */
		private static final long serialVersionUID = -4818229131330014012L;
		private double limite;	
	//constructeur
		//creation du compte
		public CompteCredit(double tauxInteret, double limite){
			super("CRED", tauxInteret, LocalDate.now());
			this.setLimite(limite);
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

	//toString implementation
		@Override
		public String toString() {
			return super.toString()+"\n\tLimite sur le compte: "+ this.limite;
		}
		
		
}
