package com.buisness.compte;

import com.buisness.client.Client;

public class CompteCheque extends Compte{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2650636843608742412L;
	
	private int nbTransGratuite;
	private int TransactionRestante;
	private double fraisTransaction;
	
	public CompteCheque(Client clientId, double solde, double fraisTransaction) {
		super(clientId, solde);
		this.setCompteId(clientId);
		this.setTransactionsGratuite(2);
		this.setFraisTransaction(fraisTransaction);
		this.TransactionRestante =this.nbTransGratuite;
	}

	protected CompteCheque(Client clientId,String numero, double solde, double fraisTransaction,int transactionRestante) {
		    super(clientId, solde);
		    this.setCompteId(clientId);
		    this.setTransactionsGratuite(2);
		    this.setFraisTransaction(fraisTransaction);
			this.TransactionRestante =transactionRestante;
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
		this.compteId = "CHK"+ clientId.getId() ;
		
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
			}	else {
				
			}
		}
		throw new Exception("insuffisant funds");
		
	}

	
	
	
}
