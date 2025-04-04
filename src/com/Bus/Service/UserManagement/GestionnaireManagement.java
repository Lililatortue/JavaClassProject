package com.Bus.Service.UserManagement;

import java.util.ArrayList;

import com.Bus.Model.Client.Gestionnaire;
import com.Bus.Model.Client.Utilisateur;
import com.Bus.Model.Compte.Compte;
import com.DAL.Repository.UserRepository;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.InvariantException;
import com.DAL.Repository.Exception.KeyConstraintException;

public class GestionnaireManagement {
	private UserRepository _repo;
	private ArrayList<Gestionnaire> _gestionnaire = new ArrayList<Gestionnaire>();;
	
	public GestionnaireManagement() {

		_repo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		for(var user : _repo.read()) {
			if(user instanceof Gestionnaire) {
				_gestionnaire.add((Gestionnaire)user);
			}
		}
	}
	
	
	public void  ADDGestionnaire(Gestionnaire u) throws UserValidationException, KeyConstraintException {
		UserValidation.generalValidation(u);
		_repo.create(u);
	}
	

	public void  DeleteGestionnaire(Gestionnaire u) throws UserValidationException, InvariantException {
		UserValidation.generalValidation(u);
		_repo.delete(u);
	}
	
	public ArrayList<Gestionnaire>read() {
		return _gestionnaire;
	}
	
}
