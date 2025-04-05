package com.Bus.Model.Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteType;

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

public class Transaction implements Serializable {	

	private static final long serialVersionUID = 2923352023714175172L;
	
	// Identifiant du client concerné
	private int idClient;
	private CompteType compteType;
	// Date et heure de la transaction
	private LocalDate dateTransaction;
	// Montant de la transaction
	private double montant;
	//type de transaction
	private TransactionType transactionType;
	/*
	 * Constructeur de la classe Transaction.
	 */
	
	public Transaction(int Id,CompteType compteType, double montant,TransactionType type) {	
		//la clee unique de transaction est le id du client ET le type de compte
		this.setClientId(Id);
		this.compteType = compteType;
		this.setDate(LocalDate.now());
		this.transactionType = type;
		this.setMontant(montant);
	}
	
	// GETTERS
	public int getClientId() {
		return this.idClient;
	} // Retourne l'ID du client associé à la transaction
	
	public LocalDate getDate() {
		return this.dateTransaction;
	} // Retourne la date de la transaction
	
	public double getMontant() {
		return this.montant;
	} // Retourne le montant de la transaction
	
	public CompteType getCompteType() {
		return this.compteType;
	}
	
	public TransactionType getTransactionType() {
		return this.transactionType;
	}
	// SETTERS
	public void setClientId(int id) {
		this.idClient =id;
	}// Définit l'ID du client en fonction du compte concerné
	
	public void setDate(LocalDate date) {
		this.dateTransaction = date;
	} // Définit la date de la transaction
	
	public void setMontant(double montant) {
		this.montant = montant;
	} // Définit le montant de la transaction
	
	public void setType(CompteType type) {
		this.compteType = type;
	}
	
	public void setTransactionType(TransactionType type) {
		this.transactionType=type;
	}
	
	/*
	 * Redéfinition de la méthode toString pour afficher les détails de la transaction.
	 */
	@Override
	public String toString() {
		return "Client #" + idClient + " | Compte: " + this.getCompteType()
			   + " | Montant: " + montant + " $ | Date: " + dateTransaction+" $ | transaction type: " + transactionType;
	}
}
