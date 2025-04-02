package com.Bus.Model.Compte;

import com.Bus.Model.Client.Client;

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
	private int nbTransGratuite;
	
	// Transactions restantes gratuites
	private int TransactionRestante;
	
	// Frais pour les transactions au-delà du nombre gratuit
	private double fraisTransaction;
	
    /*
	 * Constructeur de la classe CompteCheque.
	 */
		public CompteCheque(int clientId, double solde, double fraisTransaction) {
			super(clientId, solde, CompteType.CHCK );
			this.setTransactionsGratuite(2);
			this.setFraisTransaction(fraisTransaction);
			this.TransactionRestante =this.nbTransGratuite;
		}
		//prototype
	 public CompteCheque(CompteCheque compte) {
		    super(compte);
		    this.setTransactionsGratuite(2);
		    this.setFraisTransaction(compte.getFraisTransaction());
		    this.TransactionRestante = compte.TransactionRestante;
		}
	


		@Override
		public void deposer(double montant) {
			// TODO Auto-generated method stub
		
		}

		@Override
		protected double retirer(double montant) throws Exception{
			if(this.solde-montant>0) {
				if(this.TransactionRestante>0) {
					this.TransactionRestante-=1;
					this.solde-=montant;
					return montant;
				} else {
				
				}
			}
			throw new Exception("insuffisant funds");	
		}

	
	//getters
		public double getFraisTransaction() {
			return fraisTransaction;
		}

		public int getTransactionsGratuite() {
			return nbTransGratuite;
		}

	//setters
		public void setFraisTransaction(double fraisTransaction) {
			this.fraisTransaction = fraisTransaction;
		}
		
		public void setTransactionsGratuite(int transactionsGratuite) {
			this.nbTransGratuite = transactionsGratuite;
		}

		@Override
		public void setCompteId(Client clientId) {
			// TODO Auto-generated method stub
			
		}
	
}
