package com.Bus.Service.CompteManagement;

import com.Bus.Service.UserManagement.ClientManagement;
import com.DAL.Repository.CompteRepository;

import com.Bus.Model.Compte.Compte;
import com.DAL.Repository.Connection.SerializeRecord;

//class existe afin de decoupler les compte et les client dorenavant la list de compte que le client a seras generer ici
public class CompteManagement {
	private ClientManagement _user;
	private CompteRepository _clientRepo;
	
	public CompteManagement() {
		_clientRepo = new CompteRepository(new SerializeRecord<Compte>(".\\src\\data\\user\\CompteList.ser"));
	}
	
	
	public void attachAccount() {
		
	}
	
	public void CreateAccount() {
		
	}
	

}
