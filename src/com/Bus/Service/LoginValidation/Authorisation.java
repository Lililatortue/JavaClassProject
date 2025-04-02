package com.Bus.Service.LoginValidation;

import com.Ui.ClientHub.*;
import com.Ui.GestionnaireHub.GestionnaireHub;

/*
 * Classe responsable de l'autorisation des utilisateurs après authentification.
 * 
 * Cette classe décide de l'interface utilisateur à afficher en fonction du type
 * d'utilisateur (Client ou Gestionnaire). Elle fait partie de la chaîne de responsabilité.
 */

public class Authorisation extends ConnectionHandler {
	
	/*
     * Gère l'autorisation de l'utilisateur et affiche l'interface correspondante.
     */
	@Override
	public void Handle(Request request) {
		System.out.println("Authorisation en cours...\n");
		
		// Vérifie que l'utilisateur est bien authentifié avant de procéder
        if (request.getUser() == null) {
            System.out.println("Erreur : Aucun utilisateur trouvé dans la requête.");
            // throw new InvalidAuthorizationException()
            return;
        }
        
        // Redirige l'utilisateur vers l'interface appropriée
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
