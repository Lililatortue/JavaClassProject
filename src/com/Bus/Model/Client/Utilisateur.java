package com.Bus.Model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Classe abstraite représentant un utilisateur dans le système bancaire.
 * Elle hérite de la classe Personne et ajoute des attributs spécifiques à l'utilisateur,
 * tels que le NIP (Numéro d'Identification Personnel) et le type d'utilisateur.
 * Cette classe est utilisée comme base pour les classes spécifiques comme Client et Gestionnaire.
 */
public abstract class Utilisateur extends Personne {
	
	private static final long serialVersionUID = -655492106551936827L;
	
	// NIP (Numéro d'Identification Personnel) pour l'utilisateur
	protected String nip;
	// Type d'utilisateur
	private String role;
	protected String email;
	
	/**
	 * Constructeur de la classe Utilisateur.
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param nip
	 * @param type
	 * @param email
	 */
	public Utilisateur(int id, String nom, String prenom, String adresse, String nip, String type, String email) {
        super(id, nom, prenom, adresse);
        this.nip = nip;
        this.role = type;
        this.email = email;
    }
	/**
	 * prototype
	 * @param user
	 * @throws SQLException 
	 */
	public Utilisateur(ResultSet rs) throws SQLException {
		super( rs.getInt("USR_ID"),rs.getString("USR_NOM"), rs.getString("USR_PRENOM"), rs.getString("USR_ADRESSE"));
		this.nip = rs.getString("USR_NIP");
        this.role = rs.getString("USR_ROLE");
        this.email = rs.getString("USR_EMAIL");
    }
	/**
	 * prototype
	 * @param user
	 */
	public Utilisateur(Utilisateur user) {
		super(user);
		this.nip = user.nip;
        this.role = user.role;
        this.email = user.email;
    }

	// GETTERS

	public String getNip() {
		return nip;
	} // Retourne le NIP (Numéro d'Identification Personnel) de l'utilisateur
	
	public String getRole() {
		return this.role;

	}
	public String getEmail() {
		return this.email;
	} // Retourne le type de l'utilisateur

	@Override
	public String toString() {
		return "Id: "+id+" Nom: "+ nom+" Prenom: "+ prenom;
	}
}
