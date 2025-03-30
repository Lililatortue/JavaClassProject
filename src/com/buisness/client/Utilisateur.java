package com.buisness.client;

public abstract class Utilisateur extends Personne {
	
	private static final long serialVersionUID = -655492106551936827L;
	protected String nip;
	public String type;
	public Utilisateur(int id, String nom, String prenom, String adresse, String nip, String type) {
        super(id, nom, prenom, adresse);
        this.nip = nip;
        this.type = type;
    }

	public String getNip() {
		return nip;
	}
	
	public String getType() {
		return this.type;
	}
}
