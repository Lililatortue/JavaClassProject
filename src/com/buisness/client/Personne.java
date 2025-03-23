package com.buisness.client;

import java.io.Serializable;

public abstract class Personne implements Serializable{
	
	private static final long serialVersionUID = -7143856928958832012L;
	protected int id;
    protected String nom;
    protected String prenom;
    protected String adresse;
    
    
    public Personne(int id, String nom, String prenom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }


	public int getId() {
		return id;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	

	public abstract void afficherDetails();
	 
}
