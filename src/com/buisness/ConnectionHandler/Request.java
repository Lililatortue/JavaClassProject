package com.buisness.ConnectionHandler;

import com.buisness.client.Utilisateur;

public class Request {
	private int _id;
	private String _password;
	
	private Utilisateur _user;
	public Request(int id, String psw) {
		this._id= id;
		this._password = psw;
	}
	
	public int getId() {
		return this._id;
	}
	public String getPassword() {
		return this._password;
	}
	
	public void setUser(Utilisateur user) {
		this._user = user;
	}
	public Utilisateur getUser() {
		return this._user;
	}
}
