package com.Bus.Service.LoginValidation;

import com.Bus.Model.Client.Utilisateur;
import com.DAL.Repository.UserRepository;
import com.DAL.Repository.Connection.SerializeRecord;
import com.DAL.Repository.Exception.*;
import com.Ui.GestionnaireHub.GestionnaireHub;

/*
 * Gestionnaire d'authentification des utilisateurs, faisant partie de la chaîne de responsabilité.
 * 
 * Cette classe vérifie si les identifiants fournis dans une Request correspondent
 * à un utilisateur enregistré dans le système (base de données sérialisée).
 * 
 * Si l'utilisateur est trouvé et les identifiants sont valides, l'objet Utilisateur
 * est associé à la requête, et la gestion de la connexion est transmise à l'élément suivant
 * de la chaîne via super.Handle(request).
 * 
 * Un cas spécial est prévu : si l'ID saisi est 0, l'interface GestionnaireHub
 * s'ouvre automatiquement (mode développeur/test).
 */
public class Authentification extends ConnectionHandler{
	
	// L'utilisateur authentifié lors de la validation
	private Utilisateur _user;
	
	/**
	 * Gère la requête d'authentification
	 * 
	 * @param request - La requête de connexion contenant l'ID et le NIP
	 * @throws Exception - Si la requête est invalide ou si les identifiants ne correspondent pas
	 */
	@Override
	public void Handle(final Request request) throws Exception  {	
		// Cas spécial : accès développeur
		if(request.getId()==0) {
			GestionnaireHub gHub =new GestionnaireHub();
			gHub.setVisible(true);
			return;
		}
		// Initialisation du repository utilisateur
		UserRepository repo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		// Recherche de l'utilisateur correspondant à l'ID
		this._user = repo.findFirst((u)-> u.getId()==request.getId());
		// Vérification du NIP
		if(_user !=null && request.getPassword().equals(_user.getNip())) {
			request.setUser(_user); // Authentification réussie
			super.Handle(request); // Transmission de la requête
		} else {
			throw new InvariantException("Invalid Credential");
		}
	}
}
