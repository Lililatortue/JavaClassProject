package com.Bus.Model.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAL.Repository.Exception.InvariantException;

/*
 * Classe représentant un compte courant (CompteCheque) dans le système bancaire.
 * 
 * Un compte courant permet de gérer les transactions courantes telles que les dépôts et les retraits. 
 * Cette classe étend la classe abstraite Compte et ajoute des fonctionnalités spécifiques aux comptes chèques,
 * telles que la gestion du nombre de transactions gratuites et des frais associés aux transactions supplémentaires.
 */

public class CompteCheque extends Compte{

	private static final long serialVersionUID = 2650636843608742412L;
	
	// Nombre de transactions gratuites
	private final int nbTransGratuite = 2;
	
	// Transactions restantes gratuites
	private int TransactionRestante;
	
	// Frais pour les transactions au-delà du nombre gratuit
	private double fraisTransaction;

	/**
	 * Constructeur de la classe CompteCheque.
	 * @param clientId
	 * @param solde
	 * @param fraisTransaction
	 */
		public CompteCheque(int clientId,double solde, double fraisTransaction) {
			super(clientId, solde, CompteType.CHEQUE);
			this.setFraisTransaction(fraisTransaction);
			this.TransactionRestante =this.nbTransGratuite;
		}
		
		/**
		 * contructor de Oracle sql
		 * @param rs
		 * @throws SQLException
		 */
		public CompteCheque(ResultSet rs) throws SQLException {
			super(rs);
			this.setFraisTransaction(rs.getInt("CPT_FRAIS_TRANSACTION"));
		}
		/**
		 * prototype
		 * @param compte
		 */
	 public CompteCheque(CompteCheque compte) {
		    super(compte);
		    this.setFraisTransaction(compte.getFraisTransaction());
		    this.TransactionRestante = compte.TransactionRestante;
		}
	
		@Override
		public void deposer(double montant) throws InvariantException {
			if(montant>0) {
					this.solde+=montant;
					return;
			}	
			throw new InvariantException("insuffisant funds");
		}

		@Override
		public double retirer(double montant) throws InvariantException{
			
			if(this.TransactionRestante>0) 
			{
				//retire une transaction gratuite
				this.TransactionRestante-=1;
				if(this.solde-montant>0) {
						
					this.solde-=montant;
					return montant;	
				} else {
					throw new InvariantException("insuffisant funds");
				}		
			} 
			else 
			{
				if(this.solde-(montant+fraisTransaction)>0) {
						
					this.solde-=(montant+fraisTransaction);
					return montant;
				} else {
					throw new InvariantException("insuffisant funds");
				}
			}
		} 


	
	//getters
		public double getFraisTransaction() {
			return fraisTransaction;
		}
		
		public int getTransactionsRestante() {
			return TransactionRestante;
		}
	//setters
		public void setFraisTransaction(double fraisTransaction) {
			this.fraisTransaction = fraisTransaction;
		}

		@Override 
		public String toString() {
			return "\n\ttype de compte: "+super.getType()+ " Solde: "+super.solde+""; 
		}
}
