package com.buisness.client;

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
	public String type;
	
	 /*
	 * Constructeur de la classe Utilisateur.
	 */
	public Utilisateur(int id, String nom, String prenom, String adresse, String nip, String type) {
        super(id, nom, prenom, adresse);
        this.nip = nip;
        this.type = type;
    }

	// GETTERS
	public String getNip() {
		return nip;
	} // Retourne le NIP (Numéro d'Identification Personnel) de l'utilisateur
	
	public String getType() {
		return this.type;
	} // Retourne le type de l'utilisateur
}
