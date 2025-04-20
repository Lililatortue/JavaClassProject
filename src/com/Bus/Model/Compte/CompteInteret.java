package com.Bus.Model.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import com.Bus.Event.IInterestEvent;
import com.DAL.Repository.Exception.InvariantException;

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
		
	// Limite d'achat ou de retrait sur le compte
	protected Double limite=null;
	
	/**
	 * contructeur par default
	 * @param clientId
	 * @param tauxInteret
	 * @param type
	 */
	public CompteInteret(int clientId, Double limite, double tauxInteret, CompteType type) {
		super(clientId, 0, type);
		this.setCurrentMonth(LocalDate.now());
		this.setYear(LocalDate.now());
		this.setTauxInteret(tauxInteret);
		this.setLimite(limite);
	}
	
	/**
	 * contructor de Oracle sql
	 * @param rs
	 * @throws SQLException
	 */
	public CompteInteret(ResultSet rs) throws SQLException {
		super(rs);
		LocalDate date=rs.getDate("CPT_DATE_OUV") == null ? LocalDate.now(): rs.getDate("CPT_DATE_OUV").toLocalDate();
		this.setCurrentMonth(date);
		this.setYear(date);
		this.setTauxInteret(rs.getInt("CPT_TAUX"));
		double val = rs.getDouble("CPT_LIMITE");
		this.setLimite(rs.wasNull() ? null : val);
		this.update();
	}
	
	/**
	 * prototype
	 * @param compte
	 */
	public CompteInteret(CompteInteret compte) {
		super(compte);
		this.moiActuel = compte.getMois();
		this.year = compte.year;
		this.tauxInteretParAnne = compte.tauxInteretParAnne;
		this.limite = compte.getLimite();
		this.update();
	}	
		
	// MÉTHODES DE GESTION DES OPÉRATIONS BANCAIRES
	/*
	 * Dépôt d'un montant sur le compte.
	 * Si des intérêts sont dus, le dépôt est d'abord utilisé pour les payer avant d'être ajouté au solde.
	 */
	@Override
	public void deposer(double montant) throws InvariantException {	
		if(this.interetMensuelDu <= montant) {
			montant -= this.interetMensuelDu;
			this.interetMensuelDu = 0.0;
			this.solde-=montant;
		} else {
			this.interetMensuelDu -= montant;
			return;
		}	
	}
	
	
	// SETTERS
	// Met à jour le mois actuel du compte si la date est valide
	public void setCurrentMonth(LocalDate currentDate) {
		if(currentDate.getYear()>=LocalDate.now().getYear()) {
				this.moiActuel = currentDate.getMonth();
		}	
	} 
		
	// Définit l'année de référence du compte
	public void setYear(LocalDate currentDate) {
		this.year=currentDate.getYear();	
	} 
	
	// Définit le taux d'intérêt annuel du compte	
	private void setTauxInteret(double tauxInteret) {
		this.tauxInteretParAnne=tauxInteret;
	} 
	
	// Définit la limite du compte	
	private void setLimite(Double limite) {
		this.limite=limite;
	} 
		 
		
		
	// GETTERS
	// Retourne le mois actuel du compte
	public Month getMois(){
		return this.moiActuel;
	} 
	
	
	// Retourne le taux d'intérêt annuel appliqué au compte
	public double getTauxInteretAnnuel() {
		return this.tauxInteretParAnne;
	} 
	
	
	// Retourne le taux d'intérêt annuel appliqué au compte
	public double getInteretDu() {
		return this.interetMensuelDu;
	} 
	
	// Définit la limite du compte	
	public Double getLimite() {
		return this.limite;
	} 	
	// MÉTHODE DE MISE À JOUR
		/**
		 * Mise à jour de l'intérêt mensuel.
		 * Appelée pour ajuster les intérêts lorsque le mois change.
		 */
	@Override
	public void update() {
		if(this.getMois().getValue() < LocalDate.now().getMonth().getValue())
			this.setInteretMensuelDu();
	}
		
	// MÉTHODE D'INTÉRÊT
	private void setInteretMensuelDu() {
		this.solde+=(this.getTauxInteretAnnuel() / 12) * this.solde;
	}
	
	
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
