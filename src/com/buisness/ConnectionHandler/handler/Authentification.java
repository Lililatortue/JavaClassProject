package com.buisness.ConnectionHandler.handler;

import com.DAL.repo.UserRepository;
import com.DAL.repo.strategy.SerializeRecord;
import com.buisness.ConnectionHandler.ConnectionHandler;
import com.buisness.ConnectionHandler.Request;
import com.buisness.client.Utilisateur;

import GUI.GestionnaireHub;

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
	public void Handle(final Request request) {	
		// Création du repository utilisateur avec sérialisation
		UserRepository repo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		
		// Recherche du premier utilisateur correspondant à l'ID de la requête
		this._user = repo.findFirst((u)-> u.getId()==request.getId());
		
		// Cas spécial : ID 0 pour tester, ouvre l'interface de gestion
		if(request.getId()==0) {
			GestionnaireHub hub =new GestionnaireHub();
			hub.setVisible(true);
			return;
		}
		
		// Vérification des identifiants
		if(_user !=null && request.getPassword().equals(_user.getNip())) {
			// Associe l'utilisateur validé à la requête
			request.setUser(_user);
			
			// Passe la requête au prochain gestionnaire dans la chaîne
			super.Handle(request);
		} else {
			System.out.print("Invalid Credential");
			// throw new InvalidConnectionCredentialException("Invalid IDs");
		}
	}
}
