package com.Bus.Service.CompteManagement;

import java.util.ArrayList;
import com.Bus.Model.Compte.*;
import com.Bus.Service.UserManagement.UserValidationException;
import com.DAL.Repository.CompteRepository;
import com.DAL.Repository.Exception.*;

public class CompteRequestManagement {
	private CompteRepository _compteRepo;

	public CompteRequestManagement() {
		_compteRepo = new CompteRepository();
	}
	
	
	public void  ApprouveRequestCompte(Compte compte) throws UserValidationException, InvariantException  {
		_compteRepo.create(compte);
	}

	
	public void  DenyRequestCompte(Compte compte) throws InvariantException  {
		_compteRepo.delete(compte);
	}
	
	
	public void  AccountRequest(Compte u) throws KeyConstraintException  {
		_compteRepo.create(u);
	}
	
	
	public ArrayList<Compte> read(){
		return _compteRepo.read();
	}
}
