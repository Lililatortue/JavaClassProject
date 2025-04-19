package com.Bus.Service.CompteManagement;
import com.DAL.Repository.CompteRepository;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Transaction.Transaction;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

//class existe afin de decoupler les compte et les client dorenavant la list de compte que le client a seras generer ici
public class CompteManagement {
	private CompteRepository _compteRepo;
	public CompteManagement() {
		_compteRepo = new CompteRepository();
	
	}
	public CompteManagement(Predicate<Compte> predicate) {
		_compteRepo = new CompteRepository();
	
	}
	
	public void CreateAccount(Compte compte) throws KeyConstraintException {
		_compteRepo.create(compte);
	}
	
	public void DeleteAccount(Compte compte) throws InvariantException {
		_compteRepo.delete(compte);
	}
	/**
	 * 
	 * @param transaction
	 * @throws Exception
	 */
	public void deposer(Transaction transaction) throws InvariantException {
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
			throw new InvariantException("aucun compte existe");
		}else {
			compte.deposer(transaction.getMontant());
		}	
		_compteRepo.update(compte);
	}
	/**
	 * 
	 * @param transaction
	 * @throws Exception
	 */
	public void retirer(Transaction transaction) throws InvariantException {
		Compte compte = null;
				
		_compteRepo.update(compte);
	}
	
	public ArrayList<Compte> getAccount() {
		return this._compteRepo.read();
	}
}
