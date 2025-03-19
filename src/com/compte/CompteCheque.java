package com.compte;

public class CompteCheque extends AbstractCompte{
	private int nbTransGratuite;
	private double fraisTransaction;
	
	public CompteCheque(String numero, Double solde, int transactionGratuite, double fraisTransaction) {
		super(numero, solde);
		setFraisTransaction(transactionGratuite);
		setTransactionsGratuite(transactionGratuite);
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

	
	
	

	
	
}
