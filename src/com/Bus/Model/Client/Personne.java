package com.Bus.Model.Client;

import java.io.Serializable;

 /*
 * Classe abstraite représentant une personne dans le système bancaire.
 * Elle implémente l'interface Serializable afin de permettre la persistance des données.
 * 
 * Cette classe sert de base pour les différentes catégories d'utilisateurs, comme les clients et les gestionnaires.
 * Elle définit des attributs communs à toutes les personnes ainsi qu'une méthode abstraite.
 */

public abstract class Personne implements Serializable{
	
	private static final long serialVersionUID = -7143856928958832012L;
	
	// Attributs communs à toutes les personnes
	protected int id;
    protected String nom;
    protected String prenom;
    protected String adresse;
    
     /*
     * Constructeur de la classe Personne.
     */
    public Personne(int id, String nom, String prenom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }
    //prototype
    public Personne(Personne personne) {
        this.id = personne.id;
        this.nom = personne.nom;
        this.prenom = personne.prenom;
        this.adresse = personne.adresse;
    }

    // GETTERS
	public int getId() {
		return id;
	} // Retourne l'identifiant unique de la personne

	public String getNom() {
		return nom;
	} // Retourne le nom de la personne

	public String getPrenom() {
		return prenom;
	} //Retourne le prénom de la personne

	public String getAdresse() {
		return adresse;
	} // Retourne l'adresse de la personne

	// SETTERS
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	} // Modifie l'adresse de la personne
	
	 /*
     * Méthode abstraite devant être implémentée par les classes enfants.
     * Elle doit afficher les détails de la personne.
     */
	public abstract void afficherDetails();
	 
}
