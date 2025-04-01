package ui;

import business.Client;
import business.Compte;
import business.CompteCheque;
import business.CompteCredit;
import business.DemandeClient;
import business.Depot;
import business.FileManager;
import business.LigneDeCredit;
import business.Retrait;
import business.Virement;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MenuClient extends JFrame {
	
	private static final long serialVersionUID = 7953756532647647360L;
	
	private Client client;
	private java.util.List<Client> clients;
	
	public MenuClient(Client client, java.util.List<Client> clients) {
		this.client = client;
	    this.clients = clients;

        setTitle("Menu Client - Fortis Bank");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));

        JLabel lblBienvenue = new JLabel("Bienvenue " + client.getNom(), SwingConstants.CENTER);
        lblBienvenue.setFont(new Font("Arial", Font.BOLD, 16));
        getContentPane().add(lblBienvenue, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel(new GridLayout(0, 1, 10, 10));
        getContentPane().add(panelButtons, BorderLayout.CENTER);

        // Boutons
        JButton btnSoldeTotal = new JButton("Consulter solde total");
        btnSoldeTotal.addActionListener(_ -> afficherSolde());

        JButton btnDepot = new JButton("Effectuer un dépôt");
        btnDepot.addActionListener(_ -> effectuerDepot());

        JButton btnRetrait = new JButton("Effectuer un retrait");
        btnRetrait.addActionListener(_ -> effectuerRetrait());

        JButton btnVirement = new JButton("Effectuer un virement");
        btnVirement.addActionListener(_ -> effectuerVirement());

        JButton btnHistorique = new JButton("Voir l'historique des transactions");
        btnHistorique.addActionListener(_ -> afficherHistorique());
        
        JButton btnDemandeAjout = new JButton("Demander un compte supplémentaire");
        btnDemandeAjout.addActionListener(_ -> demanderAjoutCompte());

        JButton btnDemandeFermeture = new JButton("Demander la fermeture d’un compte");
        btnDemandeFermeture.addActionListener(_ -> demanderFermetureCompte());

        JButton btnFermetureTotale  = new JButton("Demander la fermeture complète");
        btnFermetureTotale.addActionListener(_ -> demanderFermetureTotale());
        
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(_ -> dispose());

        // Ajout
        panelButtons.add(btnSoldeTotal);
        panelButtons.add(btnDepot);
        panelButtons.add(btnRetrait);
        panelButtons.add(btnVirement);
        panelButtons.add(btnHistorique);
        panelButtons.add(btnDemandeAjout);
        panelButtons.add(btnDemandeFermeture);
        panelButtons.add(btnFermetureTotale);
        panelButtons.add(btnQuitter);
    }
	
	
	
	// Solde Cheque
	private void afficherSolde() {
        double total = client.consulterSolde();
        JOptionPane.showMessageDialog(this, "Solde total : " + total + " $");
    }
	
	
	// Deposit
	private void effectuerDepot() {
        Compte compte = choisirCompte("Choisir un compte pour le dépôt");
        if (compte == null) return;

        String montantStr = JOptionPane.showInputDialog(this, "Montant à déposer :");
        try {
            double montant = Double.parseDouble(montantStr);
            new Depot(genererId(), montant, compte).executer();
            
            FileManager.sauvegarderClients(clients, "data/clients.dat");
            
            JOptionPane.showMessageDialog(this, "Dépôt effectué !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Montant invalide");
        }
    }
	
	
	// Retrait
	private void effectuerRetrait() {
	    Compte compte = choisirCompte("Choisir un compte pour le retrait");
	    if (compte == null) return;

	    String montantStr = JOptionPane.showInputDialog(this, "Montant à retirer :");
	    try {
	        double montant = Double.parseDouble(montantStr);
	        Retrait retrait = new Retrait(genererId(), montant, compte);
	        if (retrait.executer()) {
	        	FileManager.sauvegarderClients(clients, "data/clients.dat");
	            JOptionPane.showMessageDialog(this, "Retrait effectué !");
	        } else {
	            JOptionPane.showMessageDialog(this,
	                "Retrait refusé : fonds insuffisants ou dépassement de limite",
	                "Erreur de retrait",
	                JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Montant invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	// Virement compte -> compte
	private void effectuerVirement() {
	    Compte source = choisirCompte("Compte source");
	    if (source == null) return;

	    Compte destination = choisirCompte("Compte destination");
	    if (destination == null || destination == source) {
	        JOptionPane.showMessageDialog(this, "Compte destination invalide");
	        return;
	    }

	    String montantStr = JOptionPane.showInputDialog(this, "Montant à transférer :");
	    try {
	        double montant = Double.parseDouble(montantStr);

	        Virement virement = new Virement(genererId(), montant, source, destination);
	        if (virement.executer()) {
	        	FileManager.sauvegarderClients(clients, "data/clients.dat");
	            JOptionPane.showMessageDialog(this, "Virement effectué !");
	        } else {
	            JOptionPane.showMessageDialog(this, "Virement refusé : solde insuffisant ou montant invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
	        }

	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Montant invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	private void afficherHistorique() {
	    StringBuilder sb = new StringBuilder("Historique de vos comptes\n\n");

	    for (Compte compte : client.getComptes()) {
	        sb.append("Compte : ").append(compte.getNumero()).append("\n");
	        sb.append(compte.afficherHistoriqueTexte()).append("\n");

	        // Ajout alerte de solde
	        if (compte instanceof CompteCheque && compte.getSolde() < 50) {
	            sb.append("Alerte : Solde faible pour ce compte chèque !\n");
	        } else if (compte instanceof CompteCredit && compte.getSolde() > 0) {
	            sb.append("Alerte : Vous devez ").append(compte.getSolde()).append(" $ à la banque (Crédit)\n");
	        } else if (compte instanceof LigneDeCredit && compte.getSolde() > 0) {
	            sb.append("Alerte : Dette active sur votre ligne de crédit : ").append(compte.getSolde()).append(" $\n");
	        }
	        sb.append("--------------------------------------------------\n");
	    }
	    JTextArea area = new JTextArea(sb.toString());
	    area.setEditable(false);
	    area.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    JScrollPane scroll = new JScrollPane(area);
	    scroll.setPreferredSize(new Dimension(600, 400));
	    JOptionPane.showMessageDialog(this, scroll, "Historique des Transactions", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	private void demanderAjoutCompte() {
	    String[] options = {"CHEQUE", "EPARGNE", "CREDIT", "LIGNE", "DEVISE"};
	    String choix = (String) JOptionPane.showInputDialog(
	            this,
	            "Type de compte souhaité :",
	            "Demande de compte",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]
	    );

	    if (choix != null) {
	        DemandeClient demande = new DemandeClient(
	                client.getId(),
	                "AJOUT",
	                choix,
	                java.time.LocalDate.now()
	        );

	        List<DemandeClient> demandes = FileManager.chargerDemandesClients("data/demandes_clients.dat");
	        if (demandes == null) demandes = new java.util.ArrayList<>();

	        demandes.add(demande);
	        FileManager.sauvegarderDemandesClients(demandes, "data/demandes_clients.dat");

	        JOptionPane.showMessageDialog(this, "Demande envoyée au gestionnaire");
	    }
	}
	
	
	private void demanderFermetureCompte() {
	    if (client.getComptes().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Aucun compte trouvé");
	        return;
	    }

	    String[] options = client.getComptes().stream()
	            .map(Compte::getNumero)
	            .toArray(String[]::new);

	    String choix = (String) JOptionPane.showInputDialog(this,
	            "Choisissez un compte à fermer :",
	            "Demande de fermeture",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]);

	    if (choix == null) return;

	    // Vérifier si c’est un compte chèque
	    Compte compteChoisi = client.getComptes().stream()
	            .filter(c -> c.getNumero().equals(choix))
	            .findFirst()
	            .orElse(null);

	    if (compteChoisi instanceof CompteCheque) {
	        JOptionPane.showMessageDialog(this, "Le compte chèque ne peut pas être fermé depuis cette interface");
	        return;
	    }

	    DemandeClient demande = new DemandeClient(
	            client.getId(),
	            "FERMETURE",
	            choix,
	            false,
	            java.time.LocalDate.now()
	    );

	    FileManager.ajouterDemandeClient(demande);
	    JOptionPane.showMessageDialog(this, "Demande envoyée au gestionnaire");
	}
	
	private void demanderFermetureTotale() {
	    int confirm = JOptionPane.showConfirmDialog(
	        this,
	        "Êtes-vous sûr de vouloir demander la fermeture de TOUS vos comptes ?",
	        "Confirmation",
	        JOptionPane.YES_NO_OPTION
	    );

	    if (confirm == JOptionPane.YES_OPTION) {
	        DemandeClient demande = new DemandeClient(
	                client.getId(),
	                "FERMETURE_TOTALE",
	                null, // pas de numéro spécifique
	                true,
	                java.time.LocalDate.now()
	        );

	        FileManager.ajouterDemandeClient(demande);
	        JOptionPane.showMessageDialog(this, "Demande de fermeture totale envoyée au gestionnaire");
	    }
	}
	
	
	// Generate random ID
	private static int genererId() {
		return (int) (Math.random() * 100000);
	}
	
	
	
	// Compte Mapper
	private Compte choisirCompte(String titre) {
	    if (client.getComptes().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Aucun compte disponible");
	        return null;
	    }

	    // Numéro + solde (et limite pour les crédits)
	    String[] options = client.getComptes().stream().map(c -> {
	            String info = c.getNumero() + " | Solde : " + String.format("%.2f", c.getSolde()) + " $";
	            if (c instanceof CompteCredit credit) {
	                info += " | Limite : " + credit.getLimiteCredit() + " $";
	            } else if (c instanceof LigneDeCredit ligne) {
	                info += " | Limite : " + ligne.getLimiteCredit() + " $";
	            }
	            return info;
	        }).toArray(String[]::new);

	    String choix = (String) JOptionPane.showInputDialog(this, titre, "Comptes",JOptionPane.PLAIN_MESSAGE, null,options, options[0]);

	    if (choix == null) return null;
	    String numero = choix.split("\\|")[0].trim();

	    return client.getComptes().stream().filter(c -> c.getNumero().equals(numero)).findFirst().orElse(null);
	}
}
