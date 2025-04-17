package com.Bus.Model.Compte;


/*
 * Classe représentant un compte courant (CompteCheque) dans le système bancaire.
 * 
 * Ce type de compte permet de gérer des transactions régulières (dépôts et retraits)
 * avec un nombre limité de transactions gratuites. Au-delà de ce nombre,
 * des frais sont appliqués à chaque transaction supplémentaire.
 */
public class CompteCheque extends Compte{

	private static final long serialVersionUID = 2650636843608742412L;
	
	// Nombre total de transactions gratuites autorisées
	private int nbTransGratuite;
	
	// Nombre de transactions gratuites restantes
	private int TransactionRestante;
	
	// Frais appliqués à chaque transaction une fois le quota gratuit épuisé
	private double fraisTransaction;
	
	/**
 	 * Constructeur de la classe CompteCheque
 	 * 
 	 * @param clientId
 	 * @param solde
 	 * @param fraisTransaction
 	 */
	public CompteCheque(int clientId, double solde, double fraisTransaction) {
			super(clientId, solde, CompteType.CHCK );
			this.setTransactionsGratuite(2);
			this.setFraisTransaction(fraisTransaction);
			this.TransactionRestante =this.nbTransGratuite;
	}

	/**
     * Constructeur de copie
     * 
     * @param compte
     */
	 public CompteCheque(CompteCheque compte) {
		    super(compte);
		    this.setTransactionsGratuite(2);
		    this.setFraisTransaction(compte.getFraisTransaction());
		    this.TransactionRestante = compte.TransactionRestante;
		}
	
	 /**
 	  * Effectue un dépôt sur le compte
 	  * 
 	  * @param montant - Montant à déposer
 	  * @throws Exception - Si le montant est inférieur ou égal à 0
 	  */
		@Override
		public void deposer(double montant) throws Exception {
			if(montant>0) {
					this.solde+=montant;
					return;
			}	
			throw new Exception("insuffisant funds");
		}

		/**
 		 * Effectue un retrait depuis le compte, en prenant en compte les transactions gratuites restantes.
 		 * Si aucune transaction gratuite ne reste, des frais sont appliqués.
 		 *
 		 * @param montant - Montant à retirer
 		 * @return le montant effectivement retiré
 		 * @throws Exception - Si les fonds sont insuffisants ou si le montant est invalide
 		 */
		@Override
		public double retirer(double montant) throws Exception{
			
				if(this.TransactionRestante>0) 
				{
					// Utilisation d'une transaction gratuite
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
					// Frais appliqués
					if(this.solde-(montant+fraisTransaction)>0) {
						
						this.solde-=(montant+fraisTransaction);
						return montant;
					}
					else {
						throw new Exception("insuffisant funds");
					}
				}
		} 

		/**
 		 * 
 		 * @return le montant des frais de transaction
 		 */
		public double getFraisTransaction() {
			return fraisTransaction;
		}

		/**
 		 * 
 		 * @return le nombre total de transactions gratuites autorisées
 		 */
		public int getTransactionsGratuite() {
			return nbTransGratuite;
		}
		
		/**
 		 * 
 		 * @return le nombre de transactions gratuites restantes
 		 */
		public int getTransactionsRestante() {
			return TransactionRestante;
		}
	
		/**
 		 * Définit les frais de transaction à appliquer
 		 * 
 		 * @param fraisTransaction - Montant des frais
 		 */
		public void setFraisTransaction(double fraisTransaction) {
			this.fraisTransaction = fraisTransaction;
		}
		
		/**
 		 * Définit le nombre de transactions gratuites disponibles pour le compte
 		 * 
 		 * @param transactionsGratuite - Nombre de transactions gratuites
 		 */
		public void setTransactionsGratuite(int transactionsGratuite) {
			this.nbTransGratuite = transactionsGratuite;
		}

		/**
 		 * 
 		 * @return une représentation textuelle du compte courant
 		 */
		@Override 
		public String toString() {
			return "\n\ttype de compte: "+super.getType()+ " Solde: "+super.solde+""; 
		}
}
