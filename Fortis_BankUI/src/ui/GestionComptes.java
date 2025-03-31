package ui;

import javax.swing.*;
import java.awt.*;

public class GestionComptes extends JFrame {
    public GestionComptes() {
        setTitle("Gestion des Comptes");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField idClient = new JTextField();
        String[] types = {"Chèque", "Épargne", "Crédit", "Devise", "Ligne de crédit"};
        JComboBox<String> typeCompte = new JComboBox<>(types);

        JButton ouvrirBtn = new JButton("Ouvrir Compte");
        JButton fermerBtn = new JButton("Fermer Compte");

        panel.add(new JLabel("ID Client:")); panel.add(idClient);
        panel.add(new JLabel("Type de Compte:")); panel.add(typeCompte);
        panel.add(ouvrirBtn); panel.add(fermerBtn);

        add(panel);
        setVisible(true);
    }
}
