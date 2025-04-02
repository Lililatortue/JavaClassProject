package com.buisness.client;

public abstract class Utilisateur extends Personne {
	
	private static final long serialVersionUID = -655492106551936827L;
	protected String nip;
	private String type;
	protected String email;
	
	public Utilisateur(int id, String nom, String prenom, String adresse, String nip,String email, String type) {
        super(id, nom, prenom, adresse);
        this.nip = nip;
        this.type = type;
        this.email = email;
    }
	
	public Utilisateur(Utilisateur u) {
		super(u.id, u.nom, u.prenom, u.adresse);
		this.nip = u.nip;
        this.type = u.type;
        this.email = u.email;
    }
	public String getNip() {
		return nip;
	}
	
	public String getType() {
		return this.type;
	}
	public String getEmail() {
		return this.email;
	}
}
