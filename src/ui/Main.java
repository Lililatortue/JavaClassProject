package ui;

import buisness.*;
import data.Notification;

public class Main {
    public static void main(String[] args) {

        // 1. Créer le NotificationManager
        NotificationManager manager = new NotificationManager();

        // 2. Choisir la stratégie d’envoi (ici console)
        NotificationStrategy strategy = new ConsoleNotificationStrategy();

        // 3. Créer les observers : un client + un gestionnaire
        ClientNotifier clientNotifier = new ClientNotifier(1, strategy);
        ManagerNotifier managerNotifier = new ManagerNotifier(100, strategy);

        // 4. Enregistrer les observers auprès du manager
        manager.addObserver(clientNotifier);
        manager.addObserver(managerNotifier);

        // 5. Créer une notification pour le client
        Notification notifClient = new Notification("Votre transaction de 100$ est confirmée.", 1, "Client");
        manager.notifyObservers(notifClient);

        // 6. Créer une notification pour le gestionnaire
        Notification notifManager = new Notification("Demande d'ouverture d'un nouveau compte.", 100, "Gestionnaire");
        manager.notifyObservers(notifManager);
    }
}
