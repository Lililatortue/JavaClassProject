package ui;

import javax.swing.*;
import java.awt.*;

public class Operations extends JFrame {
    public Operations() {
        setTitle("Opérations Bancaires");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField idCompte = new JTextField();
        String[] operations = {"Dépôt", "Retrait", "Virement"};
        JComboBox<String> typeOp = new JComboBox<>(operations);
        JTextField montant = new JTextField();
        JTextField compteDestinataire = new JTextField();

        JButton validerBtn = new JButton("Valider");

        panel.add(new JLabel("ID Compte:")); panel.add(idCompte);
        panel.add(new JLabel("Type d'opération:")); panel.add(typeOp);
        panel.add(new JLabel("Montant:")); panel.add(montant);
        panel.add(new JLabel("Compte Dest. (si virement):")); panel.add(compteDestinataire);
        panel.add(new JLabel()); panel.add(validerBtn);

        add(panel);
        setVisible(true);
    }
}
