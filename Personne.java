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
    
    /**
     * Constructeur de la classe Personne
     * 
     * @param id
     * @param nom
     * @param prenom
     * @param adresse
     */
    public Personne(int id, String nom, String prenom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }
    
    /**
     * Constructeur de copie
     * 
     * @param personne
     */
    public Personne(Personne personne) {
        this.id = personne.id;
        this.nom = personne.nom;
        this.prenom = personne.prenom;
        this.adresse = personne.adresse;
    }

    /**
     * 
     * @return l'identifiant unique de la personne
     */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return le nom de la personne
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @return le prénom de la personne
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * 
	 * @return l'adresse de la personne
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse de la personne
	 * 
	 * @param adresse - Nouvelle adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/*
	 * Méthode abstraite devant être implémentée par les classes enfant
	 */
	public abstract void afficherDetails();
	 
}
