package com.compte;

import java.time.LocalDate;
import java.time.Month;

import com.Event.TimeObserver;

/*
 * 0.005% de taux interet mensuelle
 * 
 * 
 * */
public class CompteEpargne extends CompteDeCredit  {
	//variable
		private double limite;
		public CompteEpargne(String n, double ti, double l) {
			super(n, ti, LocalDate.now());
			this.limite = l;
		}
		protected CompteEpargne(String n, double ti, LocalDate cd, double l) {
			super(n, ti, cd);
			this.limite = l;
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
	
}
