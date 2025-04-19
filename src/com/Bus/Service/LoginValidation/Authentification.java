package com.Bus.Service.LoginValidation;

import javax.security.auth.login.CredentialException;

import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.UserRepository;

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
		// Création du repository utilisateur avec sérialisation
		UserRepository repo = new UserRepository();
		
		// Recherche du premier utilisateur correspondant à l'ID de la requête
		this._user = repo.findOne(request.getEmail());

		
		
		// Vérification des identifiants
		if(_user !=null && request.getPassword().equals(_user.getNip())) {
			// Associe l'utilisateur validé à la requête
			request.setUser(_user);
			// Passe la requête au prochain gestionnaire dans la chaîne
			super.Handle(request);
		} else {
			throw new CredentialException("Invalid Credential");
		}
	}
}
