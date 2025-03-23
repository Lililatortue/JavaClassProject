package com.compte;

public class CompteCheque extends Compte{
	private int nbTransGratuite;
	private int TransactionRestante;
	private double fraisTransaction;
	
	public CompteCheque(String n, Double s, int tg, double ft) {
		super(n, s);
		setTransactionsGratuite(tg);
		setFraisTransaction(ft);
		this.TransactionRestante =this.nbTransGratuite;
	}

	protected CompteCheque(String n, Double s, int tg, double ft,int tr) {
		
			super(n, s);
			setTransactionsGratuite(tg);
			setFraisTransaction(ft);
			this.TransactionRestante =tr;
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
