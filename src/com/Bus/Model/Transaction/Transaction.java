package com.Bus.Model.Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteType;

/*
 * Classe abstraite représentant une transaction bancaire.
 * 
 * Cette classe définit les propriétés et comportements communs à toutes les transactions. 
 * 
 * Une transaction contient les informations suivantes :
 *   Identifiant du client concerné
 *   Type de compte impliqué
 *   Date de la transaction
 *   Montant de la transaction
 *   Type de transaction (dépôt, retrait...)
 */
public class Transaction implements Serializable {	

	private static final long serialVersionUID = 2923352023714175172L;
	
	// Identifiant du client concerné par la transaction
	private int idClient;
	
	// Type de compte impliqué dans la transaction
	private CompteType compteType;
	
	// Date à laquelle la transaction a été effectuée
	private LocalDate dateTransaction;
	
	// Montant de la transaction
	private double montant;
	
	// Type de transaction
	private TransactionType transactionType;
	
	/**
 	 * Constructeur de la classe Transaction
 	 * 
 	 * @param Id - L'identifiant du client associé
 	 * @param compteType - Le type de compte concerné
 	 * @param montant - Le montant de la transaction
 	 * @param type - Le type de la transaction
 	 */
	public Transaction(int Id,CompteType compteType, double montant,TransactionType type) {	
		this.setClientId(Id);
		this.compteType = compteType;
		this.setDate(LocalDate.now());
		this.transactionType = type;
		this.setMontant(montant);
	}
	
	/**
 	 * 
 	 * @return l'identifiant du client
 	 */
	public int getClientId() {
		return this.idClient;
	}
	
	/**
 	 * 
 	 * @return la date de la transaction
 	 */
	public LocalDate getDate() {
		return this.dateTransaction;
	}
	
	/**
 	 * 
 	 * @return le montant impliqué dans la transaction
 	 */
	public double getMontant() {
		return this.montant;
	}
	
	/**
 	 * 
 	 * @return le type de compte associé à la transaction
 	 */
	public CompteType getCompteType() {
		return this.compteType;
	}
	
	/**
 	 * 
 	 * @return le type de transaction effectuée
 	 */
	public TransactionType getTransactionType() {
		return this.transactionType;
	}
	
	/**
 	 * Définit l’identifiant du client
 	 * 
 	 * @param id - Identifiant du client
 	 */
	public void setClientId(int id) {
		this.idClient =id;
	}
	
	/**
 	 * Définit la date de la transaction
 	 * 
 	 * @param date - Date de la transaction
 	 */
	public void setDate(LocalDate date) {
		this.dateTransaction = date;
	}
	
	/**
 	 * Définit le montant de la transaction
 	 * 
 	 * @param montant - Montant à fixer
 	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	/**
 	 * Définit le type de compte impliqué
 	 * 
 	 * @param type - Type de compte
 	 */
	public void setType(CompteType type) {
		this.compteType = type;
	}
	
	/**
 	 * Définit le type de transaction
 	 * 
 	 * @param type - Type de transaction
 	 */
	public void setTransactionType(TransactionType type) {
		this.transactionType=type;
	}
	
	/**
 	 * 
 	 * @return une représentation textuelle de la transaction
 	 */
	@Override
	public String toString() {
		return "Client #" + idClient + " | Compte: " + this.getCompteType()
			   + " | Montant: " + montant + " $ | Date: " + dateTransaction+" $ | transaction type: " + transactionType;
	}
}
