package com.Bus.Service.LoginValidation;

import com.Ui.ClientHub.*;
import com.Ui.GestionnaireHub.GestionnaireHub;

/*
 * Gestionnaire d'autorisation post-authentification dans la chaîne de responsabilité.
 * 
 * Cette classe décide de l'interface utilisateur à afficher en fonction du type
 * d'utilisateur (Client ou Gestionnaire).
 */
public class Authorisation extends ConnectionHandler {
	
	/**
     * Gère l'autorisation de l'utilisateur et affiche l'interface graphique appropriée
     * 
     * @param request - La requête contenant l'utilisateur authentifié
     */
	@Override
	public void Handle(Request request) {
		System.out.println("Authorisation en cours...\n");
		// Vérifie la présence de l'utilisateur dans la requête
        if (request.getUser() == null) {
            System.out.println("Erreur : Aucun utilisateur trouvé dans la requête.");
         // throw new InvalidAuthorizationException() // À implémenter si besoin
            return;
        }
        // Redirection selon le type d'utilisateur
		switch(request.getUser().getType()) {
		case "Client" :{
			ClientHub hub = new ClientHub(request.getUser());
			hub.setVisible(true);
			break;
		}
		case "Gestionnaire":{
			GestionnaireHub hub = new GestionnaireHub(/*request.getUser()*/);
			hub.setVisible(true);
			break;
		}
		default: //throw new InvalidAuthorizationException()
		}
	}
}
