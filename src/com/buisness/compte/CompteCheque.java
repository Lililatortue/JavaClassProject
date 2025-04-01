package com.buisness.compte;

import com.buisness.client.Client;

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
	 * Initialise un compte avec un solde initial et des frais de transaction.
	 * Le nombre de transactions gratuites est fixé à 2 par défaut.
	 */
	public CompteCheque(Client clientId, double solde, double fraisTransaction) {
		super(clientId, solde);
		this.setCompteId(clientId);
		this.setTransactionsGratuite(2);
		this.setFraisTransaction(fraisTransaction);
		this.TransactionRestante =this.nbTransGratuite;
	}

	/*
	 * Constructeur de la classe CompteCheque avec un numéro de compte spécifié.
	 * Initialise un compte avec un solde initial, des frais de transaction et un nombre de transactions restantes.
	 */
	protected CompteCheque(Client clientId,String numero, double solde, double fraisTransaction,int transactionRestante) {
		    super(clientId, solde);
		    this.setCompteId(clientId);
		    this.setTransactionsGratuite(2);
		    this.setFraisTransaction(fraisTransaction);
			this.TransactionRestante =transactionRestante;
	}
	
	// GETTERS
	public double getFraisTransaction() {
		return fraisTransaction;
	} // Retourne les frais associés aux transactions supplémentaires

	public int getTransactionsGratuite() {
		return nbTransGratuite;
	} // Retourne le nombre de transactions gratuites

	// SETTERS
	public void setFraisTransaction(double fraisTransaction) {
		this.fraisTransaction = fraisTransaction;
	} // Définit les frais de transaction pour les opérations au-delà du nombre gratuit
	
	public void setTransactionsGratuite(int transactionsGratuite) {
		this.nbTransGratuite = transactionsGratuite;
	} // Définit le nombre de transactions gratuites
	
	@Override
	public void setCompteId(Client clientId) {
		this.compteId = "CHK"+ clientId.getId() ;
	} // Définit l'identifiant du compte en fonction de l'identifiant du client
	
	// MÉTHODES DE GESTION DES OPÉRATIONS BANCAIRES
	/*
	 * Méthode de dépôt
	 */
	@Override
	public void deposer(double montant) {
	}

	/*
	 * Méthode de retrait sur le compte.
	 * Si le solde est suffisant et qu'il reste des transactions gratuites, le retrait est effectué sans frais.
	 * Au-delà des transactions gratuites, des frais sont appliqués.
	 */
	@Override
	protected double retirer(double montant) throws Exception{
		if(this.solde-montant>0) {
			if(this.TransactionRestante>0) {
				this.TransactionRestante-=1;
				this.solde-=montant;
				return montant;
			}	else {
				// Appliquer des frais si les transactions gratuites sont épuisées
				
			}
		}
		throw new Exception("insuffisant funds");
		
	}

	
	
	
}
