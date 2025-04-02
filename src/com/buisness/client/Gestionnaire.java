package com.buisness.client;

import java.io.Serializable;
import java.util.List;
import com.buisness.compte.Compte;

public class Gestionnaire extends Utilisateur implements Serializable {
	
	
	private static final long serialVersionUID = 263713853896394328L;
	
	
	public Gestionnaire(int id, String nom, String prenom, String adresse, String nip,String email) {
        super(id, nom, prenom, adresse, nip, email, "Gestionnaire");
    }
	
	
    
    // Créer un client
    public void creerClient(List<Client> clients, Client client) {
        clients.add(client);
        System.out.println("Client " + client.getNom() + " créé avec succès");
    }
    
    
    // Modifier un client
    public void modifierClient(Client client, String email, String telephone, String adresse) {
        //client.setEmail(email);
        client.setTelephone(telephone);
        client.setAdresse(adresse);
        System.out.println("Informations du client " + client.getNom() + " mises à jour");
    }
    
    // Supprimer un client
    public void supprimerClient(List<Client> clients, Client client) {
        if (clients.remove(client)) {
            System.out.println("Client " + client.getNom() + " supprimé avec succès");
        } else {
            System.out.println("Client non trouvé");
        }
    }
    
    
    // Approuver l'ouverture d'un compte
    public void approuverCompte(Client client, Compte compte) {
        client.ajouterCompte(compte);
        System.out.println("Compte " + compte.getType() + " approuvé pour le client " + client.getNom());
    }
    
    
    // Utilisation de la classe Rapport
    public void genererRapports(List<Client> clients) {
        Rapport.genererRapport(clients);
    }
    
    
    @Override
    public void afficherDetails() {
        System.out.println("Gestionnaire : " + nom + " " + prenom +
                           " | ID: " + id +
                           " | Adresse: " + adresse +
                           " | Rôle: " + role);
    }
}
