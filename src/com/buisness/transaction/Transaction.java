package com.buisness.transaction;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.buisness.client.Client;
import com.buisness.compte.Compte;

public abstract class Transaction implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2923352023714175172L;
	
	
	
	// Déclaration des attributs privés
		private int idClient;
		private String compteId;
		private Date dateTransaction;
		private double montant;
		private Transaction_type type;
	
	// Méthodes publiques getters / setters
		public int getClientId() { return this.idClient; }
		public void setClientId(Compte compte) { this.idClient = compte.getClientId(); }
	
	
		public String getCompteId() { return this.compteId; }
		public void setCompteId(Compte compteId) { this.compteId = compteId.getCompteId(); }
	
		public Date getDate() { return this.dateTransaction; }
		public void setDate(Date date) { this.dateTransaction = date; }
	
		public double getMontant() { return this.montant; }
		public void setMontant(double montant) { this.montant = montant; }
	
	// Constructeurs
	
	public Transaction(Compte compte, Date date, double montant) {
		this.setClientId(compte);
		this.setCompteId(compte);
		this.setDate(date);
		this.montant = montant;
	}
		
	// Méthode abstraite
	
	public abstract void executer();

	// Override pour le formatage
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "Transaction #" + "to implement" + " | Compte: " + compteId + 
			   " | Montant: " + montant + "$ | Date: " + sdf.format(dateTransaction);
	}
}
