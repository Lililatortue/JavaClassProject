package com.Bus.Model.Client;

 /*
 * Classe abstraite représentant un utilisateur dans le système bancaire.
 * Elle hérite de la classe Personne et ajoute des attributs spécifiques à l'utilisateur,
 * tels que le NIP (Numéro d'Identification Personnel) et le type d'utilisateur.
 * 
 * Cette classe est utilisée comme base pour les classes spécifiques comme Client et Gestionnaire.
 */

public abstract class Utilisateur extends Personne {
	
	private static final long serialVersionUID = -655492106551936827L;
	
	// NIP (Numéro d'Identification Personnel) pour l'utilisateur
	protected String nip;
	// Type d'utilisateur
	private String type;
	protected String email;
	
	 /*
	 * Constructeur de la classe Utilisateur.
	 */
	public Utilisateur(int id, String nom, String prenom, String adresse, String nip, String type, String email) {
        super(id, nom, prenom, adresse);
        this.nip = nip;
        this.type = type;
        this.email = email;
    }
	//prototype
	public Utilisateur(Utilisateur user) {
		super(user);
		this.nip = user.nip;
        this.type = user.type;
        this.email = user.email;
    }

	// GETTERS

	public String getNip() {
		return nip;
	} // Retourne le NIP (Numéro d'Identification Personnel) de l'utilisateur
	
	public String getType() {
		return this.type;

	}
	public String getEmail() {
		return this.email;
	} // Retourne le type de l'utilisateur

	@Override
	public String toString() {
		return "Id: "+id+" Nom: "+ nom+" Prenom: "+ prenom;
	}
}
