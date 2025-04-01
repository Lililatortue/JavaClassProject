package com.buisness.client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.buisness.compte.*;

 /*
 * La classe Client représente un utilisateur ayant un rôle de client dans le système bancaire.
 * Elle hérite de la classe Utilisateur et implémente Serializable pour permettre la persistance des données.
 * 
 * Un client possède un email, un numéro de téléphone et une liste de comptes bancaires.
 * Par défaut, un compte chèque est attribué à chaque client lors de sa création.
 */

public class Client extends Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 2445142944692786364L;
	
	// Informations personnelles du client
	private String email;
	private String telephone;
	
	// Liste des comptes associés au client
	private List<Compte> comptes;
	
	 /*
	 * Constructeur de la classe Client.
	 * Initialise un client avec ses informations personnelles et lui attribue un compte chèque par défaut.
	 */
	 public Client(int id, String nom, String prenom, String adresse, String nip, String email, String telephone) {
		 super(id, nom, prenom, adresse, nip, "Client");
	     this.email = email;
	     this.telephone = telephone;
	     this.comptes = new ArrayList<>();
	     this.setComptePreRequis(); // Attribution d'un compte chèque par défaut
	 }
	 
	 
	// GETTERS
	 public String getEmail() {
		 return email;
	 } // Retourne l'adresse email du client
	 
	 public String getTelephone() {
		 return telephone;
	 } // Retourne le numéro de téléphone du client
	 
	 public List<Compte> getComptes() {
		 return comptes;
	 } // Retourne la liste des comptes associés au client

	
	// SETTERS
	 public void setEmail(String email) {
		 this.email = email;
	 } // Met à jour l'adresse email du client
	 
	 public void setTelephone(String telephone) {
		 this.telephone = telephone;
	 } // Met à jour le numéro de téléphone du client
	 
	 private void setComptePreRequis() {
		 CompteCheque c = new CompteCheque(this, 0.0, 5.0);
		 this.comptes = new ArrayList<>();
		 this.ajouterCompte(c);
	 } // Crée et ajoute un compte chèque par défaut au client
	 
	 // MÉTHODES
	 // Ajoute un compte à la liste des comptes du client
	 public void ajouterCompte(Compte compte) {
		 comptes.add(compte);
     }
	 
	 // Calcule et retourne le solde total de tous les comptes du client
	 public double consulterSolde() {
		 double soldeTotal = 0;
		 for (Compte compte : comptes) {
			 soldeTotal += compte.getSolde();
		 }
		 return soldeTotal;
     }
    
    // Affiche les informations détaillées du client et la liste de ses comptes
	 @Override
	 public void afficherDetails() {
		 System.out.println("Client : " + nom + " " + prenom +
                            " | ID: " + id +
                            " | Adresse: " + adresse +
                            " | Email: " + email +
                            " | Téléphone: " + telephone);
		 System.out.println("Comptes du client :");
		 comptes.forEach(System.out::println);
	 }
}
