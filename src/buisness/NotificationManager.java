package buisness;


import data.Notification;
import data.NotificationData;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private List<NotificationObserver> observers = new ArrayList<>();

    // Ajouter un observer (client ou gestionnaire)
    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    // Supprimer un observer si besoin
    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    // Notifier tous les observers avec une notification
    public void notifyObservers(Notification notification) {
        // Sauvegarder la notification
        NotificationData.saveNotification(notification);

        // Informer tous les observers (clients / gestionnaires)
        for (NotificationObserver observer : observers) {
            observer.update(notification);
        }
    }
}
