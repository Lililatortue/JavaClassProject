package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import business.*;

public class MenuGestionnaire extends JFrame{


	private static final long serialVersionUID = -7917160074747081866L;
	
	 private JLabel lblStatus;
	 private Gestionnaire gestionnaire;
	 private List<Client> clients;
	 
	 
	 public MenuGestionnaire(Gestionnaire g, List<Client> listeClients) {
	        this.gestionnaire = g;
	        this.clients = listeClients;

	        setTitle("Menu Gestionnaire - Fortis Bank");
	        setSize(400, 500);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(new BorderLayout(10, 10));

	        // Accueil
	        lblStatus = new JLabel("Bienvenue " + g.getNom(), SwingConstants.CENTER);
	        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));
	        getContentPane().add(lblStatus, BorderLayout.NORTH);

	        // Boutons
	        JPanel panelButtons = new JPanel();
	        panelButtons.setLayout(new GridLayout(0, 1, 10, 10));
	        getContentPane().add(panelButtons, BorderLayout.CENTER);

	        
	        // MENU
	        
	        // BTN Création Client
	        JButton btnCreerClient = new JButton("Créer un nouveau client");
	        btnCreerClient.addActionListener(_ -> creerClient());

	        // BTN Ajouter compte à un client
	        JButton btnAjouterCompte = new JButton("Ajouter un compte à un client");
	        btnAjouterCompte.addActionListener(_ -> ajouterCompte());

	        
	        // BTN Supprimer un Compte du client
	        JButton btnSupprimerCompte = new JButton("Supprimer un compte d’un client");
	        btnSupprimerCompte.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                supprimerCompte();
	            }
	        });
	        
	        // BTN Supprimer client avec son compte chèque
	        JButton btnSupprimerClient = new JButton("Supprimer un client + compte chèque");
	        btnSupprimerClient.addActionListener(_ -> supprimerClientEtCheque());
	        
	        
	        
	        // BTN Générer un rapport global
	        JButton btnRapportGlobal = new JButton("Générer un rapport global");
	        btnRapportGlobal.addActionListener(_ -> {
	            StringBuilder rapport = new StringBuilder("Rapport Global des Clients\n\n");

	            for (Client c : clients) {
	                rapport.append(Rapport.formatClientAvecComptes(c));
	            }

	            JTextArea area = new JTextArea(rapport.toString());
	            area.setEditable(false);
	            JScrollPane scroll = new JScrollPane(area);
	            scroll.setPreferredSize(new Dimension(600, 400));

	            JOptionPane.showMessageDialog(this, scroll, "Rapport Global", JOptionPane.INFORMATION_MESSAGE);
	        });

	        
	        
	        // BTN Consulter l’activité des clients
	        JButton btnActiviteClients = new JButton("Activité des clients");
	        btnActiviteClients.addActionListener(_ -> {
	            StringBuilder rapport = new StringBuilder("Activité des clients\n\n");

	            for (Client client : clients) {
	                rapport.append("Client : ").append(client.getNom()).append(" ").append(client.getPrenom()).append("\n");
	                for (Compte compte : client.getComptes()) {
	                    rapport.append(compte.afficherHistoriqueTexte()).append("\n");
	                }
	                rapport.append("--------------------------------------------------\n");
	            }

	            JTextArea area = new JTextArea(rapport.toString());
	            area.setEditable(false);
	            JScrollPane scroll = new JScrollPane(area);
	            scroll.setPreferredSize(new Dimension(700, 400));
	            JOptionPane.showMessageDialog(this, scroll, "Activité des clients", JOptionPane.INFORMATION_MESSAGE);
	        });
	        
	        
	        
	        // BTN Rapport des soldes de comptes
	        JButton btnSoldes = new JButton("Soldes des comptes");
	        btnSoldes.addActionListener(_ -> {
	            StringBuilder rapport = new StringBuilder("Soldes des comptes\n\n");

	            for (Client client : clients) {
	                rapport.append(client.getNom()).append(" ").append(client.getPrenom()).append(" :\n");
	                for (Compte compte : client.getComptes()) {
	                    rapport.append(compte.getClass().getSimpleName())
	                           .append(" - ")
	                           .append(compte.getNumero())
	                           .append(" | Solde : ")
	                           .append(String.format("%.2f", compte.getSolde()))
	                           .append(" $\n");
	                }
	                rapport.append("\n");
	            }

	            JTextArea area = new JTextArea(rapport.toString());
	            area.setEditable(false);
	            JScrollPane scroll = new JScrollPane(area);
	            scroll.setPreferredSize(new Dimension(500, 400));

	            JOptionPane.showMessageDialog(this, scroll, "Rapport des Soldes", JOptionPane.INFORMATION_MESSAGE);
	        });

	        
	       
	        JButton btnReleves = new JButton("Relevés mensuels");
	        btnReleves.addActionListener(_ -> {
	            // Temp sorties console
	            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	            PrintStream oldOut = System.out;
	            System.setOut(new PrintStream(buffer));

	            // Rapport via les méthodes existantes
	            for (Client client : clients) {
	                System.out.println("Client : " + client.getNom() + " " + client.getPrenom());
	                for (Compte compte : client.getComptes()) {
	                    compte.afficherHistorique();
	                }
	                System.out.println("--------------------------------------------------\n");
	            }

	            // Sortie Normal
	            System.setOut(oldOut);

	            // Affichage
	            JTextArea area = new JTextArea(buffer.toString());
	            area.setEditable(false);
	            JScrollPane scroll = new JScrollPane(area);
	            scroll.setPreferredSize(new Dimension(600, 500));
	            JOptionPane.showMessageDialog(this, scroll, "Relevés Mensuels", JOptionPane.INFORMATION_MESSAGE);
	        });
	        
	        
	        JButton btnSuperviser = new JButton("Superviser comptes critiques");
	        btnSuperviser.addActionListener(_ -> {
	            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	            PrintStream ancienOut = System.out;
	            System.setOut(new PrintStream(buffer));

	            // Rapport régulier
	            gestionnaire.superviserComptesCritiques(clients);

	            // Vérification de l'inactivité des comptes en devise
	            for (Client client : clients) {
	                for (Compte compte : client.getComptes()) {
	                    if (compte instanceof CompteDevise cd && cd.estInactifDepuisUnAn()) {
	                        System.out.println("Compte en devise inactif depuis plus d’un an : " + cd.getNumero() +
	                                " | Client : " + client.getNom() + " " + client.getPrenom());
	                    }
	                }
	            }

	            System.setOut(ancienOut);

	            JTextArea area = new JTextArea(buffer.toString());
	            area.setEditable(false);
	            area.setFont(new Font("Monospaced", Font.PLAIN, 12));
	            JScrollPane scroll = new JScrollPane(area);
	            scroll.setPreferredSize(new Dimension(700, 400));

	            JOptionPane.showMessageDialog(this, scroll, "Supervision des comptes critiques", JOptionPane.INFORMATION_MESSAGE);
	        });
	        
	        
	        JButton btnVoirDemandes = new JButton("Voir demandes des clients");
	        btnVoirDemandes.addActionListener(_ -> afficherDemandesClients());
	        
	        
	        
	        JButton btnCreerGestionnaire = new JButton("Créer un nouveau gestionnaire");
	        btnCreerGestionnaire.addActionListener(_ -> creerNouveauGestionnaire());


	        JButton btnQuitter = new JButton("Quitter");
	        btnQuitter.addActionListener(_ -> dispose());
	        
	        
	        

	        // ADDS
	        panelButtons.add(btnCreerClient);
	        panelButtons.add(btnAjouterCompte);
	        panelButtons.add(btnSupprimerCompte);
	        panelButtons.add(btnSupprimerClient);
	        panelButtons.add(btnRapportGlobal);
	        panelButtons.add(btnActiviteClients);
	        panelButtons.add(btnSoldes);
	        panelButtons.add(btnReleves);
	        panelButtons.add(btnSuperviser);
	        panelButtons.add(btnVoirDemandes);
	        panelButtons.add(btnCreerGestionnaire);
	        panelButtons.add(btnQuitter);
	    }
	 
	 
	 	// Création Client
	    private void creerClient() {
	        JTextField idField = new JTextField();
	        JTextField nomField = new JTextField();
	        JTextField prenomField = new JTextField();
	        JTextField adresseField = new JTextField();
	        JTextField nipField = new JTextField();
	        JTextField emailField = new JTextField();
	        JTextField telField = new JTextField();

	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        panel.add(new JLabel("ID:"));
	        panel.add(idField);
	        panel.add(new JLabel("Nom:"));
	        panel.add(nomField);
	        panel.add(new JLabel("Prénom:"));
	        panel.add(prenomField);
	        panel.add(new JLabel("Adresse:"));
	        panel.add(adresseField);
	        panel.add(new JLabel("NIP:"));
	        panel.add(nipField);
	        panel.add(new JLabel("Email:"));
	        panel.add(emailField);
	        panel.add(new JLabel("Téléphone:"));
	        panel.add(telField);

	        int result = JOptionPane.showConfirmDialog(this, panel, "Créer un client", JOptionPane.OK_CANCEL_OPTION);
	        if (result == JOptionPane.OK_OPTION) {
	            try {
	                int id = Integer.parseInt(idField.getText());
	                String nom = nomField.getText();
	                String prenom = prenomField.getText();
	                String adresse = adresseField.getText();
	                String nip = nipField.getText();
	                String email = emailField.getText();
	                String tel = telField.getText();

	                gestionnaire.creerClientAvecCompteCheque(clients, id, nom, prenom, adresse, nip, email, tel);
	                FileManager.sauvegarderClients(clients, "data/clients.dat");
	                JOptionPane.showMessageDialog(this, "Client créé avec succès !");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
	            }
	        }
	    }
	    
	    
	    // Ajouter compte à un client
	    private void ajouterCompte() {
	        String inputId = JOptionPane.showInputDialog(this, "ID du client :");
	        if (inputId == null) return;
	        int id = Integer.parseInt(inputId);

	        Client client = clients.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
	        if (client == null) {
	            JOptionPane.showMessageDialog(this, "Client introuvable");
	            return;
	        }

	        String[] types = {"Épargne", "Crédit", "Ligne de Crédit", "Devise"};
	        String type = (String) JOptionPane.showInputDialog(this, "Type de compte :", "Compte",
	                JOptionPane.QUESTION_MESSAGE, null, types, types[0]);

	        if (type == null) return;
	        
	        // Attribution automatique du type de compte
	        String numero;
	        switch (type) {
	            case "Épargne" -> numero = "EPR-" + id;
	            case "Crédit" -> numero = "CRD-" + id;
	            case "Ligne de Crédit" -> numero = "LIG-" + id;
	            case "Devise" -> numero = "DEV-" + id;
	            default -> {
	                JOptionPane.showMessageDialog(this, "Type de compte invalide");
	                return;
	            }
	        }
	       
	        Compte compte = null;

	        switch (type) {
	            case "Épargne" -> {
	                double solde = Double.parseDouble(JOptionPane.showInputDialog(this, "Solde initial :"));
	                double taux = Double.parseDouble(JOptionPane.showInputDialog(this, "Taux annuel :"));
	                compte = new CompteEpargne(numero, solde, taux);
	            }
	            case "Crédit" -> {
	                double limite = Double.parseDouble(JOptionPane.showInputDialog(this, "Limite :"));
	                double taux = Double.parseDouble(JOptionPane.showInputDialog(this, "Taux :"));
	                compte = new CompteCredit(numero, 0, limite, taux); // solde fixe à 0
	            }
	            case "Ligne de Crédit" -> {
	                double limite = Double.parseDouble(JOptionPane.showInputDialog(this, "Limite :"));
	                double taux = Double.parseDouble(JOptionPane.showInputDialog(this, "Taux :"));
	                compte = new LigneDeCredit(numero, 0, limite, taux); // solde fixe à 0
	            }
	            case "Devise" -> {
	                double solde = Double.parseDouble(JOptionPane.showInputDialog(this, "Solde initial :"));
	                String devise = JOptionPane.showInputDialog(this, "Devise (USD, EUR...) :");
	                compte = new CompteDevise(numero, solde, devise);
	            }
	        }

	        if (compte != null) {
	            client.ajouterCompte(compte);
	            FileManager.sauvegarderClients(clients, "data/clients.dat");
	            JOptionPane.showMessageDialog(this, "Compte ajouté avec succès !");
	        }
	    }
	    
	    
	    // Supprimer un compte du client
	    private void supprimerCompte() {
	        String idStr = JOptionPane.showInputDialog(this, "ID du client :");
	        if (idStr == null) return;

	        try {
	            int id = Integer.parseInt(idStr);
	            Client client = null;

	            for (Client c : clients) {
	                if (c.getId() == id) {
	                    client = c;
	                    break;
	                }
	            }

	            if (client == null) {
	                JOptionPane.showMessageDialog(this, "Client introuvable");
	                return;
	            }

	            List<Compte> comptes = client.getComptes();
	            if (comptes.size() <= 1) {
	                JOptionPane.showMessageDialog(this, "Impossible de supprimer le seul compte du client");
	                return;
	            }

	            String[] numeros = comptes.stream()
	                .filter(c -> !(c instanceof CompteCheque))
	                .map(Compte::getNumero)
	                .toArray(String[]::new);

	            if (numeros.length == 0) {
	                JOptionPane.showMessageDialog(this, "Aucun compte à supprimer (le compte chèque ne peut pas être supprimé ici)");
	                return;
	            }

	            String choix = (String) JOptionPane.showInputDialog(this, "Sélectionner un compte :", "Comptes", JOptionPane.PLAIN_MESSAGE, null, numeros, numeros[0]);

	            if (choix != null) {
	                comptes.removeIf(c -> c.getNumero().equals(choix));
	                FileManager.sauvegarderClients(clients, "data/clients.dat");
	                JOptionPane.showMessageDialog(this, "Compte supprimé !");
	            }

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
	        }
	    }
	    
	    
	    // Supprimer client avec son compte chèque
	    private void supprimerClientEtCheque() {
	        String idStr = JOptionPane.showInputDialog(this, "ID du client à supprimer :");
	        if (idStr == null) return;

	        try {
	            int id = Integer.parseInt(idStr);
	            Client client = null;

	            for (Client c : clients) {
	                if (c.getId() == id) {
	                    client = c;
	                    break;
	                }
	            }

	            if (client == null) {
	                JOptionPane.showMessageDialog(this, "Client introuvable");
	                return;
	            }

	            // Vérifie compte chèque
	            boolean hasCheque = client.getComptes().stream().anyMatch(c -> c instanceof CompteCheque);

	            if (!hasCheque) {
	                JOptionPane.showMessageDialog(this, "Ce client n’a pas de compte chèque");
	                return;
	            }

	            int confirm = JOptionPane.showConfirmDialog(this, "Supprimer le client et son compte chèque ?", "Confirmation", JOptionPane.YES_NO_OPTION);
	            if (confirm == JOptionPane.YES_OPTION) {
	                clients.remove(client);
	                FileManager.sauvegarderClients(clients, "data/clients.dat");
	                JOptionPane.showMessageDialog(this, "Client supprimé avec succès !");
	            }

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
	        }
	    }
	    
	    
	    private void creerNouveauGestionnaire() {
	        JTextField idField = new JTextField();
	        JTextField nomField = new JTextField();
	        JTextField prenomField = new JTextField();
	        JTextField adresseField = new JTextField();
	        JTextField nipField = new JTextField();
	        JTextField roleField = new JTextField();

	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        panel.add(new JLabel("ID :"));
	        panel.add(idField);
	        panel.add(new JLabel("Nom :"));
	        panel.add(nomField);
	        panel.add(new JLabel("Prénom :"));
	        panel.add(prenomField);
	        panel.add(new JLabel("Adresse :"));
	        panel.add(adresseField);
	        panel.add(new JLabel("NIP :"));
	        panel.add(nipField);
	        panel.add(new JLabel("Rôle :"));
	        panel.add(roleField);

	        int result = JOptionPane.showConfirmDialog(this, panel, "Nouveau Gestionnaire", JOptionPane.OK_CANCEL_OPTION);
	        if (result == JOptionPane.OK_OPTION) {
	            try {
	                int id = Integer.parseInt(idField.getText());
	                String nom = nomField.getText();
	                String prenom = prenomField.getText();
	                String adresse = adresseField.getText();
	                String nip = nipField.getText();
	                String role = roleField.getText();

	                List<Gestionnaire> gestionnaires = FileManager.chargerGestionnaires("data/gestionnaires.dat");
	                if (gestionnaires == null) gestionnaires = new ArrayList<>();

	                // Si existe déjà
	                boolean existe = gestionnaires.stream().anyMatch(g -> g.getId() == id);
	                if (existe) {
	                    JOptionPane.showMessageDialog(this, "ID déjà utilisé !");
	                    return;
	                }

	                Gestionnaire nouveau = new Gestionnaire(id, nom, prenom, adresse, nip, role);
	                gestionnaires.add(nouveau);
	                FileManager.sauvegarderGestionnaires(gestionnaires, "data/gestionnaires.dat");

	                JOptionPane.showMessageDialog(this, "Gestionnaire créé avec succès !");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
	            }
	        }
	    }
	    
	    
	    private void afficherDemandesClients() {
	        List<DemandeClient> demandes = FileManager.chargerDemandesClients("data/demandes_clients.dat");
	        if (demandes == null || demandes.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Aucune demande trouvée");
	            return;
	        }

	        StringBuilder sb = new StringBuilder("Demandes des clients\n\n");
	        for (DemandeClient d : demandes) {
	            sb.append("Client ID: ").append(d.getClientId()).append("\n")
	              .append("Type de demande: ").append(d.getTypeDemande()).append("\n");

	            if (d.getTypeDemande().equalsIgnoreCase("AJOUT")) {
	                sb.append("Compte souhaité: ").append(d.getTypeCompteSouhaite()).append("\n");
	            } else {
	                sb.append("Compte concerné: ").append(d.getCompteCible()).append("\n");
	            }

	            sb.append("Date: ").append(d.getDateDemande()).append("\n");
	            sb.append("---------------------------------------------\n");
	        }
	        JTextArea area = new JTextArea(sb.toString());
	        area.setEditable(false);
	        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
	        JScrollPane scroll = new JScrollPane(area);
	        scroll.setPreferredSize(new Dimension(600, 400));

	        JOptionPane.showMessageDialog(this, scroll, "Demandes des Clients", JOptionPane.INFORMATION_MESSAGE);
	    }
}
