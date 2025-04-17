package com.Bus.Model.Client;
import java.io.Serializable;

/*
* La classe Client représente un utilisateur ayant un rôle de client dans le système bancaire.
* Elle hérite de la classe Utilisateur et implémente Serializable pour permettre la persistance des données.
* 
* Chaque client possède des informations personnelles telles que son numéro de téléphone et son adresse email.
* Par défaut, un compte chèque est attribué à chaque client lors de sa création.
*/
public class Client extends Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 2445142944692786364L;
	
	// Numéro de téléphone du client
	private String telephone;

	
	/**
 	 * Constructeur de la classe Client
 	 * 
 	 * @param id
 	 * @param nom
 	 * @param prenom
 	 * @param adresse
 	 * @param nip
 	 * @param email
 	 * @param telephone
 	 */
	 public Client(int id, String nom, String prenom, String adresse, String nip, String email, String telephone) {
		 super(id, nom, prenom, adresse, nip, "Client",email);
	     this.telephone = telephone;
	 }
	 
	 /**
 	  * Constructeur de copie
 	  * 
 	  * @param c
 	  */
	 public Client(Client c) {
		 super(c);
	     this.telephone = c.telephone;
	 }
	
	

	 public String getEmail() {
		 return email;
	 } // Retourne l'adresse email du client
	 
	 public String getTelephone() {
		 return telephone;
	 } // Retourne le numéro de téléphone du client
	 

	 
	 public void setTelephone(String telephone) {
		 this.telephone = telephone;
	 } // Met à jour le numéro de téléphone du client
	 
    
    // Affiche les informations détaillées du client et la liste de ses comptes
	 @Override
	 public void afficherDetails() {
		 System.out.println("Client : " + nom + " " + prenom +
                            " | ID: " + id +
                            " | Adresse: " + adresse +
                            " | Email: " + email +
                            " | Téléphone: " + telephone);
	 }
	 
	 /**
 	  * Représentation textuelle du client
 	  * 
 	  * @return une chaîne de caractères décrivant le client
 	  */
	 @Override 
	 public String toString() {
		 return "Client : " + nom + " " + prenom +" | ID: " + id +
                 " | Adresse: " + adresse +
                 " | Email: " + email +
                 " | Téléphone: " + telephone;
	 }
}

