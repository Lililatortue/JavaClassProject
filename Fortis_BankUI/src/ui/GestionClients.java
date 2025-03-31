package ui;

import javax.swing.*;
import java.awt.*;

public class GestionClients extends JFrame {
    public GestionClients() {
        setTitle("Gestion des Clients");
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        JTextField prenom = new JTextField();
        JTextField nom = new JTextField();
        JTextField adresse = new JTextField();
        JTextField email = new JTextField();
        JTextField telephone = new JTextField();
        JTextField nip = new JTextField();

        JButton creerBtn = new JButton("Créer");
        JButton modifierBtn = new JButton("Modifier");
        JButton supprimerBtn = new JButton("Supprimer");

        panel.add(new JLabel("Prénom:")); panel.add(prenom);
        panel.add(new JLabel("Nom:")); panel.add(nom);
        panel.add(new JLabel("Adresse:")); panel.add(adresse);
        panel.add(new JLabel("Email:")); panel.add(email);
        panel.add(new JLabel("Téléphone:")); panel.add(telephone);
        panel.add(new JLabel("NIP:")); panel.add(nip);
        panel.add(creerBtn); panel.add(modifierBtn);
        panel.add(new JLabel()); panel.add(supprimerBtn);

        add(panel);
        setVisible(true);
    }
}
