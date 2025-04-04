package com.Bus.Service.UserManagement;


import com.Bus.Model.Client.*;
import java.util.ArrayList;
import com.Bus.Model.Compte.Compte;
import com.Bus.Model.Compte.CompteCheque;
import com.DAL.Repository.*;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

public class ClientManagement {
	private UserRepository _userRepo;
	private CompteRepository _compteRepo;
	private ArrayList<Client> _client = new ArrayList<Client>();;
	
	public ClientManagement() {
		_userRepo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		_compteRepo= new CompteRepository(new SerializeRecord<Compte>(".\\src\\data\\user\\CompteList.ser"));
		for(var user : _userRepo.read()) {
			if(user instanceof Client) {
				_client.add((Client)user);
			}
		}
	}
	
	
	public void  ADDClient(Client u) throws UserValidationException, KeyConstraintException {
		UserValidation.generalValidation(u);
		_userRepo.create(u);
		_compteRepo.create(new CompteCheque(u.getId(),0,5));
		_client.add(u);
	}
	
	
	public void  ADDAccount(Compte compte) throws UserValidationException, InvariantException {
		if(_userRepo.findFirst((u)->u.getId()==compte.getClientId()) != null)
			_compteRepo.create(compte);	
		
	}
	
	
	public void  DeleteClient(Client u) throws UserValidationException, InvariantException {
		UserValidation.generalValidation(u);
		_userRepo.delete(u);
	}
	
	public ArrayList<Client>read() {
		return _client;
	}
	
	public ArrayList<Compte> getClientCompte(Client u){
		ArrayList<Compte> temp = new ArrayList<>();
		for(var item: _compteRepo.read()) {
			if(item.getClientId()==u.getId()) {
				temp.add(item);
			}
		}
		return temp;
	}
}
