package com.Bus.Service.LoginValidation;

import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.UserRepository;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.*;
import com.Ui.GestionnaireHub.GestionnaireHub;

/*
 * Classe gérant l'authentification des utilisateurs via la chaîne de responsabilité.
 * 
 * Cette classe vérifie si les identifiants fournis dans la Request correspondent à un utilisateur existant dans la base de données sérialisée.
 * Si l'authentification réussit, l'utilisateur est associé à la requête et la gestion de connexion est transmise
 * à l'élément suivant dans la chaîne.
 */


public class Authentification extends ConnectionHandler{
	
	// L'utilisateur authentifié
	private Utilisateur _user;
	
	// Gère la requête d'authentification
	@Override
	public void Handle(final Request request) throws Exception  {	
		// Cas spécial : ID 0 pour tester, ouvre l'interface de gestion

		if(request.getId()==0) {
			GestionnaireHub gHub =new GestionnaireHub();
			gHub.setVisible(true);
			return;
		}
		// Création du repository utilisateur avec sérialisation
		UserRepository repo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		
		
		
		
				
		// Recherche du premier utilisateur correspondant à l'ID de la requête
		this._user = repo.findFirst((u)-> u.getId()==request.getId());
		
		
		//segment pour tester juste rentrer 0  et le gestionnaire hub seras ouvert

		
		
		// Vérification des identifiants
		if(_user !=null && request.getPassword().equals(_user.getNip())) {
			// Associe l'utilisateur validé à la requête
			request.setUser(_user);
			// Passe la requête au prochain gestionnaire dans la chaîne
			super.Handle(request);
		} else {
			throw new InvariantException("Invalid Credential");
		}
	}
}
