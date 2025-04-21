package com.Bus.Model.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

public class Transaction {	

	
	

	private int idCompte;
	private LocalDate dateTransaction;
	private double montant;

	//les deux prochains sont optionel aucune operation depends d'eux souvent utilise pour rajouter des details

	private TransactionType transactionType;//le type de la transaction est definie par la fonction utilis

	private CompteType compteType;//extra 
	
	
	public Transaction(int Id, double montant) {	
		this.setCompteId(Id);
		this.setDate(LocalDate.now());
		this.setMontant(montant);
	}
	
	public Transaction(ResultSet rs) throws SQLException {	 
		this.setCompteId(rs.getInt("CPT_NUMERO"));
		this.setDate(rs.getDate("TRX_DATE").toLocalDate());
		this.setMontant(rs.getDouble("TRX_Montant"));
	}
	
	// GETTERS
	// Retourne l'ID du compte associé à la transaction
	public int getCompteId() {
		return this.idCompte;
	} 
	

	// Retourne la date de la transaction
	public LocalDate getDate() {
		return this.dateTransaction;
	} 
	
	public double getMontant() {
		return this.montant;
	} 
	
	public TransactionType getTransactionType() {
		return this.transactionType;
	}
	
	public CompteType getCompteType() {
		return this.compteType;
	}
	
	// SETTERS
	// Définit l'ID du client en fonction du compte concerné
	public void setCompteId(int id) {
		this.idCompte =id;
	}
	
	// Définit la date de la transaction
	public void setDate(LocalDate date) {
		this.dateTransaction = date;
	} 
	
	// Définit le montant de la transaction
	public void setMontant(double montant) {
		this.montant = montant;
	} 
	
	public void setCompteType(CompteType type) {
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
		return "CompteId #" + idCompte + " | Compte: " + this.getCompteType()
			   + " | Montant: " + montant + " $ | Date: " + dateTransaction+" $ | transaction type: " + transactionType;
	}
}
