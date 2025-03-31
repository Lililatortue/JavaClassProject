package com.buisness.compte;

import java.time.LocalDate;
import java.time.Month;
import com.Event.IInterestEvent;
import com.buisness.client.Client;
/*
 * class abstraite pour subclass l'implimentation de la fonction setInteretMensuel 
 * set la variable interetMensuelDu 
 * 
 * */
public abstract class CompteInteret extends Compte implements IInterestEvent {
	/**
	 * 
	 */
		private static final long serialVersionUID = -8200966552710101788L;
	//variable
		private int year;
		private Month moiActuel;
		private double tauxInteretParAnne;
		protected double interetMensuelDu;
		
	//constructor
		public CompteInteret(Client client_id, double tauxInteret,LocalDate dateCreation) {
			super(client_id,0);
			this.setCurrentMonth(dateCreation);
			this.setYear(dateCreation);
			this.setTauxInteret(tauxInteret);
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
			this.year=currentDate.getYear();	
		}
		
		private void setTauxInteret(double tauxInteret) {
			this.tauxInteretParAnne=tauxInteret;
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
				"\n\tDernier moi dont les taux interets ont ete charge: "+this.moiActuel+"/"+this.year+
				"\n\tTaux interet: "+this.tauxInteretParAnne+
				"\n\tInteret mensuel du: "+this.interetMensuelDu;
		}
	
}
