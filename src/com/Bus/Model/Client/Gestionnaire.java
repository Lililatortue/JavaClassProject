package com.Bus.Model.Client;

import java.io.Serializable;

 /*
 * La classe Gestionnaire représente un utilisateur ayant un rôle administratif dans le système bancaire.
 * Elle hérite de la classe Utilisateur et implémente Serializable pour permettre la persistance des données.
 * 
 * Un gestionnaire peut créer, modifier et supprimer des clients, approuver l'ouverture de comptes,
 * et générer des rapports sur les clients de la banque.
 */

public class Gestionnaire extends Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 263713853896394328L;
	
	 /*
	 * Constructeur de la classe Gestionnaire.
	 */
	public Gestionnaire(int id, String nom, String prenom, String adresse, String nip,String email) {
        super(id, nom, prenom, adresse, nip, "Gestionnaire",email);
    }
	//prototype
	public Gestionnaire(Gestionnaire user) {
        super(user);
    }
	
    // SETTERS

    
    /*
    // MÉTHODES
    // Ajoute un nouveau client à la liste des clients de la banque
    public void creerClient(List<Client> clients, Client client) {
        clients.add(client);
        System.out.println("Client " + client.getNom() + " créé avec succès");
    }
    
    // Modifie les informations d'un client existant
    public void modifierClient(Client client, String email, String telephone, String adresse) {
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setAdresse(adresse);
        System.out.println("Informations du client " + client.getNom() + " mises à jour");
    }
    
    // Supprime un client de la liste des clients de la banque
    public void supprimerClient(List<Client> clients, Client client) {
        if (clients.remove(client)) {
            System.out.println("Client " + client.getNom() + " supprimé avec succès");
        } else {
            System.out.println("Client non trouvé");
        }
    }
    
    
    // Approuve l'ouverture d'un compte pour un client
    public void approuverCompte(Client client, Compte compte) {
        client.ajouterCompte(compte);
        System.out.println("Compte " + compte.getType() + " approuvé pour le client " + client.getNom());
    }
    
    
    // Génère un rapport sur l'ensemble des clients
    public void genererRapports(List<Client> clients) {
        Rapport.genererRapport(clients);
    }
    */
    // Affiche les détails du gestionnaire
    @Override
    public void afficherDetails() {
        System.out.println("Gestionnaire : " + nom + " " + prenom +
                           " | ID: " + id +
                           " | Adresse: " + adresse);
    }
}

	
