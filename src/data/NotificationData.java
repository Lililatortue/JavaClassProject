package data;

import java.io.*;
import java.util.ArrayList;

public class NotificationData {
    private static final String FILE_PATH = "notifications.ser";

    // Sauvegarde la notification dans un fichier
    public static void saveNotification(Notification notification) {
        ArrayList<Notification> notifications = loadNotifications();
        notifications.add(notification);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(notifications);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Charge toutes les notifications depuis le fichier
    public static ArrayList<Notification> loadNotifications() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (ArrayList<Notification>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
