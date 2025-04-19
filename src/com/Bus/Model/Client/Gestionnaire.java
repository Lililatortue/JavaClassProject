package com.Bus.Model.Client;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

 /*
 * La classe Gestionnaire représente un utilisateur ayant un rôle administratif dans le système bancaire.
 * Elle hérite de la classe Utilisateur et implémente Serializable pour permettre la persistance des données.
 * 
 * Un gestionnaire peut créer, modifier et supprimer des clients, approuver l'ouverture de comptes,
 * et générer des rapports sur les clients de la banque.
 */

public class Gestionnaire extends Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 263713853896394328L;
	
	/**
	 * metadata d'un gestionnaire
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param nip
	 * @param email
	 */
	public Gestionnaire(int id, String nom, String prenom, String adresse, String nip,String email) {
        super(id, nom, prenom, adresse, nip, "Gestionnaire",email);
    }
	/**
	 * initialisation a partir des rows
	 * @param rs
	 * @throws SQLException
	 */
	public Gestionnaire(ResultSet rs) throws SQLException {
	       super(rs);
	   }
	//prototype
	public Gestionnaire(Gestionnaire user) {
        super(user);
    }
	
    @Override
    public void afficherDetails() {
        System.out.println("Gestionnaire : " + nom + " " + prenom +
                           " | ID: " + id +
                           " | Adresse: " + adresse);
    }
}

	
