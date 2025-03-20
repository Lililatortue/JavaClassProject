package ui;

import buisness.*;
import data.Notification;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {

        NotificationManager manager = new NotificationManager();
        NotificationStrategy strategy = new ConsoleNotificationStrategy();

        // Observers
        ClientNotifier clientNotifier = new ClientNotifier(1, strategy);
        ManagerNotifier managerNotifier = new ManagerNotifier(100, strategy);

        manager.addObserver(clientNotifier);
        manager.addObserver(managerNotifier);

        // MENU SIMPLE
        Scanner sc = new Scanner(System.in);

        boolean continuer = true;
        while (continuer) {
            System.out.println("\n--- TEST NOTIFICATIONS ---");
            System.out.println("1. Simuler une transaction client");
            System.out.println("2. Simuler une demande ouverture compte");
            System.out.println("3. Quitter");

            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    simulateTransaction(manager, sc);
                    break;
                case 2:
                    simulateNewAccountRequest(manager);
                    break;
                case 3:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }

        sc.close();
    }

    public static void simulateTransaction(NotificationManager manager, Scanner sc) {
        System.out.println("Entrez l'ID du client : ");
        int clientId = sc.nextInt();
        System.out.println("Entrez le montant de la transaction : ");
        double montant = sc.nextDouble();

        Notification notif = new Notification("Transaction réussie de " + montant + "$", clientId, "Client");
        manager.notifyObservers(notif);
    }

    public static void simulateNewAccountRequest(NotificationManager manager) {
        Notification notif = new Notification("Demande d'ouverture d'un compte par le client.", 100, "Gestionnaire");
        manager.notifyObservers(notif);
    }
}
