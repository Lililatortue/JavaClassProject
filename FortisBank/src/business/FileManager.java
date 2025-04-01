package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileManager {
	
	// Sauvegarde des clients
    public static void sauvegarderClients(List<Client> clients, String nomFichier) {
        verifierOuCreerDossier(nomFichier);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(clients);
            System.out.println("Sauvegarde réussie dans : " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }
    
    // Chargement des clients
    @SuppressWarnings("unchecked")
    public static List<Client> chargerClients(String nomFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            List<Client> clients = (List<Client>) ois.readObject();
            System.out.println("Chargement réussi depuis : " + nomFichier);
            return clients;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur de chargement : " + e.getMessage());
            return null;
        }
    }
    
    
    // Sauvegarde Gestionnaire
    public static void sauvegarderGestionnaires(List<Gestionnaire> liste, String fichier) {
        verifierOuCreerDossier(fichier);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(liste);
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde gestionnaires: " + e.getMessage());
        }
    }
    
    
    // Charger Gestionnaire
    @SuppressWarnings("unchecked")
    public static List<Gestionnaire> chargerGestionnaires(String fichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
            return (List<Gestionnaire>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aucun fichier de gestionnaires trouvé");
            return null;
        }
    }
    
    
    // Sauvegarde Demande Client
    public static void sauvegarderDemandesClients(List<DemandeClient> demandes, String fichier) {
    	verifierOuCreerDossier(fichier);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(demandes);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des demandes clients : " + e.getMessage());
        }
    }
    
    // Ajouter demandeClient
    public static void ajouterDemandeClient(DemandeClient nouvelleDemande) {
        String fichier = "data/demandes_clients.dat";
        verifierOuCreerDossier(fichier);
        List<DemandeClient> demandes = chargerDemandesClients(fichier);
        if (demandes == null) {
            demandes = new java.util.ArrayList<>();
        }
        demandes.add(nouvelleDemande);
        sauvegarderDemandesClients(demandes, fichier);
    }
    
    
    // Charger Demande Client
    @SuppressWarnings("unchecked")
    public static List<DemandeClient> chargerDemandesClients(String fichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
            return (List<DemandeClient>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aucune demande trouvée ou erreur de chargement : " + e.getMessage());
            return null;
        }
    }
    
    
    // Crée le dossier data
    private static void verifierOuCreerDossier(String cheminFichier) {
        File fichier = new File(cheminFichier);
        File dossier = fichier.getParentFile();
        if (dossier != null && !dossier.exists()) {
            boolean created = dossier.mkdirs();
            if (created) {
                System.out.println("Dossier 'data/' créé");
            }
        }
    } 
}
