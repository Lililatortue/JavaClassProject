package com.Bus.Service.LoginValidation;

import com.Bus.Model.Client.Utilisateur;

/*
 * Classe représentant une requête utilisateur pour la gestion des connexions.
 * 
 * Cette classe encapsule les informations nécessaires à l'authentification d'un utilisateur, 
 * incluant son identifiant et son mot de passe.
 * Une fois validée, un objet Utilisateur peut être associé à la requête.
 */

public class Request {
	
	// Email de l'utilisateur
	private String _email;
	
	// Mot de passe de l'utilisateur
	private String _password;
	
	// Objet Utilisateur associé après validation de la requête
	private Utilisateur _user;
	
	/*
     * Constructeur de la classe Request.
     */
	public Request(String email, String psw) {
		this._email= email;
		this._password = psw;
	}
	
	// GETTERS
	public String getEmail() {
		return this._email;
	} // Retourne l'identifiant de l'utilisateur
	
	public String getPassword() {
		return this._password;
	} // Retourne le mot de passe de l'utilisateur
	
	public Utilisateur getUser() {
		return this._user;
	} // Retourne l'utilisateur associé à la requête, si l'authentification a réussi
	
	// SETTERS
	public void setUser(Utilisateur user) {
		this._user = user;
	} // Associe un utilisateur validé à la requête
}
