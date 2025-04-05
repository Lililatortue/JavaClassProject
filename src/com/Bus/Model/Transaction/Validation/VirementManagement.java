package com.Bus.Model.Transaction.Validation;


import java.util.ArrayList;

import com.Bus.Model.Compte.CompteType;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.Virement;
import com.Bus.Service.CompteManagement.CompteManagement;
import com.DAL.Repository.TransactionRepository;
import com.DAL.Repository.Connection.SerializeRecord;

public class VirementManagement {
	private TransactionRepository _virement;
	private TransactionRepository _transaction;
	private CompteManagement _compte;
	
	public VirementManagement() {
		_virement = new TransactionRepository(
				new SerializeRecord<Transaction>("./src/data/user/Virement.ser") );
		_transaction = new TransactionRepository(
				new SerializeRecord<Transaction>("./src/data/user/transactionList.ser") );
		_compte = new CompteManagement();
		
	}
	
	
	public void faireVirement(Virement t) throws Exception {
		_compte.retirer(t);
		_virement.create(t);
		_transaction.create(t);
	}
	
	public void recevoirVirement(Virement v,String psw,CompteType compte) throws Exception {
		Transaction temp=v.getTransaction(psw, compte);
		_compte.deposer(temp);
		_virement.delete(v);
		_virement.commit();
		_transaction.create(temp);
	}
	
	public ArrayList<Virement> getVirement(int id) {
		ArrayList<Virement> temp = new ArrayList<Virement>();
		for(var item: _virement.read()) {
			if(item instanceof Virement && item.getClientId()==id) {
				temp.add((Virement) item);
			}
		}
		
		return temp;
	}
}
