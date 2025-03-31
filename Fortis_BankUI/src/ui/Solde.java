package ui;

import javax.swing.*;
import java.awt.*;

public class Solde extends JFrame {
    public Solde() {
        setTitle("Consultation du Solde");
        setSize(350, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField idCompte = new JTextField();
        JLabel soldeLabel = new JLabel("0.00 $");
        JButton afficherBtn = new JButton("Afficher Solde");

        afficherBtn.addActionListener(e -> {
            // Simuler la récupération du solde
            soldeLabel.setText("1234.56 $");
        });

        panel.add(new JLabel("ID Compte:")); panel.add(idCompte);
        panel.add(new JLabel("Solde:")); panel.add(soldeLabel);
        panel.add(new JLabel()); panel.add(afficherBtn);

        add(panel);
        setVisible(true);
    }
}