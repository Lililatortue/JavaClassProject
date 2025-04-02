package com.buisness.ConnectionHandler.handler;

import com.DAL.repo.UserRepository;
import com.DAL.repo.strategy.SerializeRecord;
import com.buisness.ConnectionHandler.ConnectionHandler;
import com.buisness.ConnectionHandler.Request;
import com.buisness.client.Utilisateur;

import GUI.ClientHub;
import GUI.Gestionnaire.GestionnaireHub;
/* Handler qui valide une request de connection
 * elle a pour but de valider les credentials 
 * si les credential sont valides elle envoie la requete
 * au prochain Handler
 * 
 * */
public class Authentification extends ConnectionHandler{
	
	private Utilisateur _user;
	
	public void Handle(final Request request) {	
		UserRepository repo = new UserRepository(new SerializeRecord<Utilisateur>(".\\src\\data\\user\\UserList.ser"));
		
		this._user = repo.findFirst((u)-> u.getId()==request.getId());
		
		
		//segment pour tester juste rentrer 0  et le gestionnaire hub seras ouvert
		if(request.getId()==0) {
			GestionnaireHub gHub =new GestionnaireHub();
			gHub.setVisible(true);
			return;
		}//pour tester
		
		
		if(_user !=null && request.getPassword().equals(_user.getNip())) {
			request.setUser(_user);
			super.Handle(request);
		} else {
			System.out.print("Invalid Credential");
			//throw new InvalidConnectionCredential
		}
	}
}
