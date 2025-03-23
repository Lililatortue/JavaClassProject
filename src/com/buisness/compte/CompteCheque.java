package com.buisness.compte;

public class CompteCheque extends Compte{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2650636843608742412L;
	
	private int nbTransGratuite;
	private int TransactionRestante;
	private double fraisTransaction;
	
	public CompteCheque(String numero, Double solde, double fraisTransaction) {
		super(numero, solde);
		setTransactionsGratuite(2);
		setFraisTransaction(fraisTransaction);
		this.TransactionRestante =this.nbTransGratuite;
	}

	protected CompteCheque(String numero, int i, double fraisTransaction,int transactionRestante) {
		
			super(numero, i);
			setTransactionsGratuite(2);
			setFraisTransaction(fraisTransaction);
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
