package com.Bus.Model.Compte;


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
		public void deposer(double montant) throws Exception {
			if(montant>0) {
					this.solde+=montant;
					return;
			}	
			throw new Exception("insuffisant funds");
		}

		@Override
		public double retirer(double montant) throws Exception{
			
				if(this.TransactionRestante>0) 
				{
					//retire une transaction gratuite
					this.TransactionRestante-=1;
					if(this.solde-montant>0) {
						
						this.solde-=montant;
						return montant;
						
					} else {
						throw new Exception("insuffisant funds");
					}
						
				} 
				else 
				{
					if(this.solde-(montant+fraisTransaction)>0) {
						
						this.solde-=(montant+fraisTransaction);
						return montant;
					}
					else {
						throw new Exception("insuffisant funds");
					}
				}
		} 


	
	//getters
		public double getFraisTransaction() {
			return fraisTransaction;
		}

		public int getTransactionsGratuite() {
			return nbTransGratuite;
		}
		public int getTransactionsRestante() {
			return TransactionRestante;
		}
	//setters
		public void setFraisTransaction(double fraisTransaction) {
			this.fraisTransaction = fraisTransaction;
		}
		
		public void setTransactionsGratuite(int transactionsGratuite) {
			this.nbTransGratuite = transactionsGratuite;
		}

		@Override 
		public String toString() {
			return "\n\ttype de compte: "+super.getType()+ " Solde: "+super.solde+""; 
		}
}
