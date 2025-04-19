package com.Bus.Model.Transaction.Validation;


import java.util.ArrayList;

import com.Bus.Model.Compte.CompteType;
import com.Bus.Model.Transaction.Transaction;
import com.Bus.Model.Transaction.Virement;
import com.DAL.Repository.TransactionRepository;
import com.DAL.Repository.Connection.SerializeRecord;

public class VirementManagement {
	private TransactionRepository _virement;
	private TransactionManagement _transaction;
	
	//default constructor
	public VirementManagement() {
		_virement = new TransactionRepository(
				new SerializeRecord<Transaction>("./src/data/user/Virement.ser"));
		_transaction = new TransactionManagement();	
	}
	
	//rajoute le retrait dans transaction
	public void envoyerVirement(Virement t) throws Exception {
		_virement.create(t);
		_transaction.ADDTransaction(t);
	}
	
	//rajoute le montant du retrait dans le compte choisi
	public void accepterVirement(Virement v,String psw,CompteType compte) throws Exception {
		Transaction temp=v.getTransaction(psw, compte);
		_virement.delete(v);
		_virement.commit();
		_transaction.ADDTransaction(temp);
	}
	
	//method pour recevoir toute les virement envoyer a l'utilisateur
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
