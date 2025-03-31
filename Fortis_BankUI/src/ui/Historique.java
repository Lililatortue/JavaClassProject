package ui;

import javax.swing.*;
import java.awt.*;

public class Historique extends JFrame {
    public Historique() {
        setTitle("Historique des Transactions");
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        String[] colonnes = {"Date", "Type", "Montant", "Compte"};
        String[][] donnees = {
            {"2024-03-01", "Dépôt", "500.00", "123"},
            {"2024-03-02", "Retrait", "100.00", "123"},
            {"2024-03-03", "Virement", "200.00", "123 -> 456"}
        };
        JTable table = new JTable(donnees, colonnes);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
}

