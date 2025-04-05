package com.Bus.Service.CompteManagement;

import java.util.ArrayList;
import com.Bus.Model.Compte.*;
import com.Bus.Service.UserManagement.ClientManagement;
import com.Bus.Service.UserManagement.UserValidationException;
import com.DAL.Repository.CompteRepository;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.*;

public class CompteRequestManagement {
	private CompteRepository _compteRepo;
	private ClientManagement _client;

	public CompteRequestManagement() {
		_client = new ClientManagement();
		_compteRepo = new CompteRepository(new SerializeRecord<Compte>(".\\src\\data\\user\\AccountRequest.ser"));
	}
	
	
	public void  ApprouveRequestCompte(Compte compte) throws UserValidationException, InvariantException  {
		for(var client: _client.read()) {
			if(client.getId()==compte.getClientId()) {
				_client.ADDAccount(compte);
			}
		}	
		_compteRepo.delete(compte);
		_compteRepo.commit();
	}

	
	public void  DenyRequestCompte(Compte compte) throws InvariantException  {
		_compteRepo.delete(compte);
		_compteRepo.commit();
	}
	
	public void  AccountRequest(Compte u) throws KeyConstraintException  {
		_compteRepo.create(u);
	}
	
	public ArrayList<Compte> read(){
		return _compteRepo.read();
	}
}
