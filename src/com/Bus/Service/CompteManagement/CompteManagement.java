package com.Bus.Service.CompteManagement;
import com.DAL.Repository.CompteRepository;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

//class existe afin de decoupler les compte et les client dorenavant la list de compte que le client a seras generer ici
public class CompteManagement {
	private CompteRepository _compteRepo;
	public CompteManagement() {
		_compteRepo = new CompteRepository(new SerializeRecord<Compte>(".\\src\\data\\user\\CompteList.ser"));
	
	}
	
	public void CreateAccount(Compte compte) throws KeyConstraintException {
		_compteRepo.create(compte);
		_compteRepo.commit();
	}
	
	public void DeleteAccount(Compte compte) throws InvariantException {
		_compteRepo.delete(compte);
		_compteRepo.commit();
	}
	
	public void deposer(Transaction transaction) throws Exception {
		Compte compte = null;
		
		//trouver le compte concerner
		for(var item : _compteRepo.read()) {
			if(item.getClientId()==transaction.getClientId() 
					&& item.getType() == transaction.getCompteType()) 
			{
				compte=item;
				break;
			}
		}
		if(compte == null) {
			//TODO:: exception handling
			throw new Exception("no account found");
		}else {
			compte.deposer(transaction.getMontant());
		}	
		_compteRepo.update(compte);
		_compteRepo.commit();
	}
	
	public void retirer(Transaction transaction) throws Exception {
		Compte compte = null;
		
		//trouver le compte concerner
		for(var item : _compteRepo.read()) {
			if(item.getClientId()==transaction.getClientId() 
					&& item.getType() == transaction.getCompteType()) 
			{
				compte=item;
				break;
			}
		}
		if(compte == null) {
			//TODO:: exception handling
			throw new Exception("aucun compte existe");
		}else {
			compte.retirer(transaction.getMontant());
		}	
		
		_compteRepo.update(compte);
		_compteRepo.commit();
	}
}
