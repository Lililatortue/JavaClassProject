package buisness;


import data.Notification;

public class ClientNotifier implements NotificationObserver {
    private int clientId;
    private NotificationStrategy strategy;

    public ClientNotifier(int clientId, NotificationStrategy strategy) {
        this.clientId = clientId;
        this.strategy = strategy;
    }

    @Override
    public void update(Notification notification) {
        if (notification.getUserId() == clientId && notification.getType().equals("Client")) {
            strategy.send(notification);
        }
    }
}

