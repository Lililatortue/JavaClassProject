package com.buisness.ConnectionHandler.handler;

import com.buisness.ConnectionHandler.ConnectionHandler;
import com.buisness.ConnectionHandler.Request;

import GUI.*;
import GUI.Gestionnaire.GestionnaireHub;

public class Authorisation extends ConnectionHandler {
	
	public void Handle(Request request) {
		System.out.println("authorisation\n");
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
		default: //throw new InvalidCredential();
		}
		
	}
}
