package com.Bus.Model.Transaction.Validation;


import java.util.ArrayList;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.TransactionDAO;


public class TransactionManagement {

	private TransactionDAO _transaction;
	private Compte _compte;
	public TransactionManagement(Compte compte) {
		_transaction = new TransactionDAO();
		_compte=compte;
	}
	public TransactionManagement() {
		_transaction = new TransactionDAO();
	}
	
	public void buy(Transaction t) throws Exception {
		_compte.deposer(t.getMontant());
		_transaction.buy(t);
	}
	public void sell(Transaction t) throws Exception {
		_compte.retirer(t.getMontant());
		_transaction.sell(t);
	}
	
	public ArrayList<Transaction> getCompteTransactions(Compte compte) {
		return _transaction.findOne(compte);
	}

}
