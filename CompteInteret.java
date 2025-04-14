package com.Bus.Model.Compte;

import java.time.LocalDate;
import java.time.Month;

import com.Bus.Event.IInterestEvent;

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
	
	// Montant des intérêts mensuels actuellement dus
	protected double interetMensuelDu;
		
	/**
	 * Constructeur de la classe CompteInteret
	 * 
	 * @param clientId
	 * @param tauxInteret
	 * @param type
	 */
	public CompteInteret(int clientId, double tauxInteret,CompteType type) {
		super(clientId,0,type);
		this.setCurrentMonth(LocalDate.now());
		this.setYear(LocalDate.now());
		this.setTauxInteret(tauxInteret);
	}

	/**
	 * Constructeur de copie
	 * 
	 * @param compte
	 */
	public CompteInteret(CompteInteret compte) {
		super(compte);
		this.moiActuel = compte.getMois();
		this.year = compte.year;
		this.tauxInteretParAnne = compte.tauxInteretParAnne;
		this.interetMensuelDu = compte.interetMensuelDu;
	}	
	
	// Définit les intérêts mensuels dus pour le compte; doit être implémenté par les sous-classes
	protected abstract void setInteretMensuelDu(); 
		
	/**
	 * Dépose un montant sur le compte.
	 * Si des intérêts sont dus, le montant déposé est d'abord utilisé pour les couvrir.
	 *
	 * @param montant - Montant à déposer
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
	
	/**
	 * Retire un montant du compte.
	 * Le retrait augmente le solde (cas de crédits par exemple).
	 *
	 * @param montant - Montant à retirer
	 * @return le montant effectivement retiré
	 * @throws Exception - En cas d'erreur métier (gérée par les sous-classes)
	 */
	@Override
	public double retirer(double montant) throws Exception {
		this.solde += montant;
		return montant;	
	}
	
	/**
	 * Met à jour le mois de référence pour le calcul des intérêts
	 * 
	 * @param currentDate - Date courante utilisée pour déterminer le mois
	 */
	public void setCurrentMonth(LocalDate currentDate) {
		if(currentDate.getYear()>=LocalDate.now().getYear()) {
				this.moiActuel = currentDate.getMonth();
		}	
	}
		
	/**
	 * Définit l'année de référence pour la mise à jour des intérêts
	 * 
	 * @param currentDate - Date courante
	 */
	public void setYear(LocalDate currentDate) {
		this.year=currentDate.getYear();	
	}
		
	/**
	 * Définit le taux d'intérêt annuel du compte
	 * 
	 * @param tauxInteret - Taux d'intérêt annuel
	 */
	private void setTauxInteret(double tauxInteret) {
		this.tauxInteretParAnne=tauxInteret;
	}
		 
	/**
	 * 
	 * @return le mois de la dernière mise à jour des intérêts
	 */
	public Month getMois(){
		return this.moiActuel;
	}
	
	/**
	 * 
	 * @return le taux d'intérêt annuel
	 */
	public double getTauxInteretAnnuel() {
		return this.tauxInteretParAnne;
	}
	
	/**
	 * 
	 * @return une représentation textuelle du compte à intérêt
	 */
	@Override
	public String toString() {
		return super.toString()+
			"\n\tDernier moi dont les taux interets ont ete charge: "+this.moiActuel+"/"+this.year+
			"\n\tTaux interet: "+this.tauxInteretParAnne+
			"\n\tInteret mensuel du: "+this.interetMensuelDu;
	}	
}
