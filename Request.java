package com.Bus.Service.LoginValidation;

import com.Bus.Model.Client.Utilisateur;

/*
 * Représente une requête utilisateur pour le processus d'authentification et d'autorisation.
 * 
 * Cette classe encapsule les informations nécessaires à l'authentification d'un utilisateur, 
 * incluant son identifiant et son mot de passe, dans le cadre du processus de connexion
 * Une fois validée, un objet Utilisateur peut être associé à la requête.
 */
public class Request {
	
	// Identifiant unique de l'utilisateur
	private int _id;
	
	// Mot de passe de l'utilisateur
	private String _password;
	
	// Utilisateur associé après validation de la requête
	private Utilisateur _user;
	
	/**
	 * Constructeur de la classe Request
	 * 
	 * @param id - L'identifiant unique de l'utilisateur
	 * @param psw - Le mot de passe associé à cet identifiant
	 */
	public Request(int id, String psw) {
		this._id= id;
		this._password = psw;
	}
	
	/**
	 * 
	 * @return l'identifiant de l'utilisateur
	 */
	public int getId() {
		return this._id;
	}
	
	/**
	 * 
	 * @return le mot de passe de l'utilisateur
	 */
	public String getPassword() {
		return this._password;
	}
	
	/**
	 * 
	 * @return l'utilisateur associé à la requête, si l'authentification a réussi
	 */
	public Utilisateur getUser() {
		return this._user;
	}
	
	/**
	 * Associe un utilisateur à la requête après authentification réussie
	 * 
	 * @param user - L'utilisateur validé
	 */
	public void setUser(Utilisateur user) {
		this._user = user;
	}
}
