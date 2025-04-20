package com.Bus.Service.CompteManagement;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.Bus.Model.Compte.*;
import com.Bus.Service.UserManagement.UserValidationException;
import com.DAL.Repository.CompteRequestRepository;
import com.DAL.Repository.Exception.*;

public class CompteRequestManagement {
	private CompteRequestRepository _compteRepo;
	private ArrayList<Compte> _compte;
	public CompteRequestManagement() {
		_compteRepo = new CompteRequestRepository();
		_compte=_compteRepo.read();
	}
	
	
	public void  ApprouveRequestCompte(Compte compte) throws UserValidationException, InvariantException  {
		_compteRepo.update(compte);
	}

	public void  DenyRequestCompte(Compte compte) throws InvariantException  {
		_compteRepo.delete(compte);
	}
	
	public void  AccountRequest(Compte u) throws KeyConstraintException  {
		_compteRepo.create(u);
	}

	public ArrayList<Compte> read(Predicate<Compte> predicate){
		ArrayList<Compte> temp= new ArrayList<Compte>();
		for(var item : _compte) {
			if(predicate.test(item)) {
				temp.add(item);
			}
		}
		return temp;
	}
}
