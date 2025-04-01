package com.buisness.compte;

import java.time.LocalDate;
import java.time.Month;
import com.Event.IInterestEvent;
import com.buisness.client.Client;

/*
 * Classe abstraite représentant un compte bancaire avec un taux d'intérêt.
 * Ce compte applique un intérêt mensuel et suit les intérêts dus.
 */

public abstract class CompteInteret extends Compte implements IInterestEvent {
	
	private static final long serialVersionUID = -8200966552710101788L;
	
	// Année de la dernière mise à jour des intérêts
	private int year;
	
	// Mois de la dernière mise à jour des intérêts
	private Month moiActuel;
	
	// Taux d'intérêt annuel appliqué au compte
	private double tauxInteretParAnne;
	
	// Montant des intérêts mensuels dus
	protected double interetMensuelDu;
		
	/*
	 * Constructeur du compte à intérêt.
	 */
	public CompteInteret(Client client_id, double tauxInteret,LocalDate dateCreation) {
		super(client_id,0);
		this.setCurrentMonth(dateCreation);
		this.setYear(dateCreation);
		this.setTauxInteret(tauxInteret);
	}
		
	/*
	 * Définit les intérêts mensuels dus pour le compte.
	 * Cette méthode est spécifique aux sous-classes et doit être implémentée.
	 */
	protected abstract void setInteretMensuelDu(); 
		
	// MÉTHODES DE GESTION DES OPÉRATIONS BANCAIRES
	/*
	 * Dépôt d'un montant sur le compte.
	 * Si des intérêts sont dus, le dépôt est d'abord utilisé pour les payer avant d'être ajouté au solde.
	 */
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
	
	/*
	 * Retrait d'un montant du compte.
	 * Vérifie si le solde est suffisant avant d'autoriser le retrait.
	 */
	@Override
	protected double retirer(double montant) throws Exception {
		this.solde += montant;
		return montant;	
	}
	
	// SETTERS
	public void setCurrentMonth(LocalDate currentDate) {
		if(currentDate.getYear()>=LocalDate.now().getYear()) {
				this.moiActuel = currentDate.getMonth();
		}	
	} // Met à jour le mois actuel du compte si la date est valide
		
	public void setYear(LocalDate currentDate) {
		this.year=currentDate.getYear();	
	} // Définit l'année de référence du compte
		
	private void setTauxInteret(double tauxInteret) {
		this.tauxInteretParAnne=tauxInteret;
	} // Définit le taux d'intérêt annuel du compte
		 
	// GETTERS
	public Month getMois(){
		return this.moiActuel;
	} // Retourne le mois actuel du compte
	
	public double getTauxInteretAnnuel() {
		return this.tauxInteretParAnne;
	} // Retourne le taux d'intérêt annuel appliqué au compte
	
	/**
	 * Redéfinition de la méthode toString pour afficher les détails du compte à intérêt.
	 */
	@Override
	public String toString() {
		return super.toString()+
			"\n\tDernier moi dont les taux interets ont ete charge: "+this.moiActuel+"/"+this.year+
			"\n\tTaux interet: "+this.tauxInteretParAnne+
			"\n\tInteret mensuel du: "+this.interetMensuelDu;
	}	
}
