package com.buisness.ConnectionHandler;

import com.buisness.client.Utilisateur;

/*
 * Classe représentant une requête utilisateur pour la gestion des connexions.
 * 
 * Cette classe encapsule les informations nécessaires à l'authentification d'un utilisateur, 
 * incluant son identifiant et son mot de passe.
 * Une fois validée, un objet Utilisateur peut être associé à la requête.
 */

public class Request {
	
	// Identifiant unique de l'utilisateur
	private int _id;
	
	// Mot de passe de l'utilisateur
	private String _password;
	
	// Objet Utilisateur associé après validation de la requête
	private Utilisateur _user;
	
	/*
     * Constructeur de la classe Request.
     */
	public Request(int id, String psw) {
		this._id= id;
		this._password = psw;
	}
	
	// GETTERS
	public int getId() {
		return this._id;
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
