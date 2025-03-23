package com.compte;

import java.time.LocalDate;
import java.time.Month;
import com.Event.IInterestEvent;
/*
 * class abstraite pour subclass l'implimentation de la fonction setInteretMensuel 
 * set la variable interetMensuelDu 
 * 
 * */
public abstract class CompteDeCredit extends Compte implements IInterestEvent {
	//variable
		private int year;
		private Month moiActuel;
		private double tauxInteretParAnne;
		protected double interetMensuelDu;
		
	//constructor
		public CompteDeCredit(String n, double ti,LocalDate cd) {
			super(n,0);
			this.setCurrentMonth(cd);
			this.setYear(cd);
			this.setTauxInteret(ti);
		}
	//fonction abstraite
		protected abstract void setInteretMensuelDu(); 
		
	//Compte implementation
		@Override
		public void deposer(double montant) {
			if(this.interetMensuelDu <= montant) {
				montant -= this.interetMensuelDu;
				this.interetMensuelDu = 0.0;
			} else {
				this.interetMensuelDu -= montant;
			}
			this.solde-=montant;
		}
		@Override
		protected double retirer(double montant) throws Exception {
			this.solde += montant;
			return montant;	
		}
	
	//setters
		public void setCurrentMonth(LocalDate currentDate) {
			if(currentDate.getYear()>=LocalDate.now().getYear()) {
					this.moiActuel = currentDate.getMonth();
			}	
		}
		
		public void setYear(LocalDate currentDate) {
			this.year=currentDate.getYear()-LocalDate.now().getYear();	
		}
		
		private void setTauxInteret(double tauxInteret) {
			this.interetMensuelDu=tauxInteret;
		}
		 
	//getters
		public Month getMois(){
			return this.moiActuel;
		}
		
		public double getTauxInteretAnnuel() {
			return this.tauxInteretParAnne;
		}
	//state
	@Override
		public String toString() {
			return super.toString()+
				"\nDernier moi dont les taux interets ont ete charge: "+this.moiActuel+"/"+this.year+
				"\nTaux interet: "+this.interetMensuelDu+
				"\nInteret mensuel du: "+this.interetMensuelDu;
		}
	
}
