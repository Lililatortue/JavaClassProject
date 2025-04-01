package com.buisness.transaction;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.buisness.client.Client;
import com.buisness.compte.Compte;

/*
 * Classe abstraite représentant une transaction bancaire.
 * 
 * Cette classe définit les propriétés et comportements communs à toutes les transactions. 
 * Elle est destinée à être étendue par des classes concrètes.
 * 
 * Les transactions stockent des informations essentielles telles que :
 *   L'identifiant du client concerné
 *   L'identifiant du compte impacté
 *   La date de la transaction
 *   Le montant impliqué
 *   Le type de transaction
 */

public abstract class Transaction implements Serializable {	

	private static final long serialVersionUID = 2923352023714175172L;
	
	// Identifiant du client concerné
	private int idClient;
	
	// Identifiant du compte impacté
	private String compteId;
	
	// Date et heure de la transaction
	private Date dateTransaction;
	
	// Montant de la transaction
	private double montant;
	
	// Type de transaction
	private Transaction_type type;
	
	/*
	 * Constructeur de la classe Transaction.
	 */
	public Transaction(Compte compte, Date date, double montant) {
		this.setClientId(compte);
		this.setCompteId(compte);
		this.setDate(date);
		this.montant = montant;
	}
	
	// GETTERS
	public int getClientId() {
		return this.idClient;
	} // Retourne l'ID du client associé à la transaction
	
	public String getCompteId() {
		return this.compteId;
	} // Retourne l'identifiant du compte concerné
	
	public Date getDate() {
		return this.dateTransaction;
	} // Retourne la date de la transaction
	
	public double getMontant() {
		return this.montant;
	} // Retourne le montant de la transaction
	
	// SETTERS
	public void setClientId(Compte compte) {
		this.idClient = compte.getClientId();
	} // Définit l'ID du client en fonction du compte concerné
	
	public void setCompteId(Compte compteId) {
		this.compteId = compteId.getCompteId();
	} // Définit l'identifiant du compte en fonction du compte concerné
	
	public void setDate(Date date) {
		this.dateTransaction = date;
	} // Définit la date de la transaction
	
	public void setMontant(double montant) {
		this.montant = montant;
	} // Définit le montant de la transaction
	
	public void setType(Transaction_type type) {
		this.type = type;
	}
	
	// MÉTHODE ABSTRAITE
	// Exécute la transaction; sera implémentée dans les classes dérivées
	public abstract void executer();

	/*
	 * Redéfinition de la méthode toString pour afficher les détails de la transaction.
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "Transaction #" + "to implement" + " | Compte: " + compteId + 
			   " | Montant: " + montant + "$ | Date: " + sdf.format(dateTransaction);
	}
}
