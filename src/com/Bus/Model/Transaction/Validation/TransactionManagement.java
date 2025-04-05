package com.Bus.Model.Transaction.Validation;


import java.util.ArrayList;

import com.Bus.Model.Compte.CompteType;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.TransactionType;
import com.Bus.Service.CompteManagement.CompteManagement;
import com.DAL.Repository.TransactionRepository;
import com.DAL.Repository.Connection.*;

public class TransactionManagement {

	private TransactionRepository _transaction;
	private CompteManagement _compte;
	
	public TransactionManagement() {
		_transaction = new TransactionRepository(
				new SerializeRecord<Transaction>("./src/data/user/transactionList.ser"));
		_compte = new CompteManagement();
	}
	
	
	public void ADDTransaction(Transaction t) throws Exception {
		//update le solde du compte
		if(t.getTransactionType()==TransactionType.depot) {
			_compte.deposer(t);
		} else if(t.getTransactionType()==TransactionType.retrait) {
			_compte.retirer(t);
		}	
		//rajouter la transaction
		_transaction.create(t);
	}
	
	public ArrayList<Transaction> getSpecifiqueTransactions(int id, CompteType type) {
		ArrayList<Transaction> temp = new ArrayList<Transaction>();

		for(var item : _transaction.read() ) {
			if(item.getClientId()==id
					&& item.getCompteType() == type)
			{
				temp.add(item);
			}
		}
		
		return temp;
	}

}
