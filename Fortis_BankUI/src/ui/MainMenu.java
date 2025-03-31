package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Fortis Bank - Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JButton gestionClientsBtn = new JButton("Gestion Clients");
        JButton gestionComptesBtn = new JButton("Gestion Comptes");
        JButton operationsBtn = new JButton("Opérations Bancaires");
        JButton soldeBtn = new JButton("Consultation Solde");
        JButton historiqueBtn = new JButton("Historique Transactions");

        gestionClientsBtn.addActionListener(e -> new GestionClients());
        gestionComptesBtn.addActionListener(e -> new GestionComptes());
        operationsBtn.addActionListener(e -> new Operations());
        soldeBtn.addActionListener(e -> new Solde());
        historiqueBtn.addActionListener(e -> new Historique());

        panel.add(gestionClientsBtn);
        panel.add(gestionComptesBtn);
        panel.add(operationsBtn);
        panel.add(soldeBtn);
        panel.add(historiqueBtn);

        add(panel);
        setVisible(true);
    }
}