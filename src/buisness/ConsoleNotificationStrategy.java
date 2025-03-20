package buisness;



import data.Notification;

public class ConsoleNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(Notification notification) {
        System.out.println("[" + notification.getDate() + "] Notification " + notification.getType() +
                " : " + notification.getMessage());
    }
}
