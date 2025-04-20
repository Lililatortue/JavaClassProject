package com.Bus.Service.UserManagement;


import com.Bus.Model.Client.*;
import java.util.ArrayList;
import com.Bus.Model.Compte.Compte;
import com.DAL.Repository.*;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

public class ClientManagement {
	private UserRepository _userRepo;
	private CompteRepository _compteRepo;
	private ArrayList<Client> _client = new ArrayList<Client>();;
	
	public ClientManagement() {
		_userRepo = new UserRepository();
		_compteRepo = new CompteRepository();
		//_compteRepo= new CompteRepository();
		for(var user : _userRepo.read()) {
			if(user instanceof Client) {
				_client.add((Client)user);
			}
		}
	}
	
	
	public void  ADDClient(Client u) throws UserValidationException, KeyConstraintException {
		UserValidation.generalValidation(u);
		_userRepo.create(u);
		_client.add(u);
	}
	
	public void  DeleteClient(Client u) throws UserValidationException, InvariantException {
		UserValidation.generalValidation(u);
		_userRepo.delete(u);
	}
	
	public ArrayList<Client>read() {
		return _client;
	}
	
	public ArrayList<Compte> readAccounts(int id){
		return _compteRepo.findOne(id);
	}
}
