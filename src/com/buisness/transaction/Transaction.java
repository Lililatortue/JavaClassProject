package com.buisness.transaction;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction implements Serializable {

	// -- 1 -- 
	// UID pour la sérialisation
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2923352023714175172L;
	
	
	// -- 2 --
	// Déclaration des attributs privés
	
	private int id;
	
	private Date date;
	
	private double montant;
	
	private String numeroCompte;
	
	
	// -- 3 --
	// Méthodes publiques getters / setters
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public Date getDate() { return this.date; }
	public void setDate(Date date) { this.date = date; }
	
	public double getMontant() { return this.montant; }
	public void setMontant(double montant) { this.montant = montant; }
	
	public String getNumeroCompte() { return this.numeroCompte; }
	public void setNumeroCompte(String numeroCompte) { this.numeroCompte = numeroCompte; }
	
	
	// -- 4 --
	// Constructeurs
	
	public Transaction() {
		this.id = 0;
		this.date = new Date();
		this.montant = 0.00;
		this.numeroCompte = "C000";
	}
	
	public Transaction(int id, Date date, double montant, String numeroCompte) {
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.numeroCompte = numeroCompte;
	}
		
	
	// -- 5 --
	// Méthode abstraite
	
	public abstract void executer();
		
	
	// -- 6 --
	// Override pour le formatage
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "Transaction #" + id + " | Compte: " + numeroCompte + 
			   " | Montant: " + montant + "$ | Date: " + sdf.format(date);
	}
}
