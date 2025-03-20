package buisness;



import data.Notification;

public class ManagerNotifier implements NotificationObserver {
    private int managerId;
    private NotificationStrategy strategy;

    public ManagerNotifier(int managerId, NotificationStrategy strategy) {
        this.managerId = managerId;
        this.strategy = strategy;
    }

    @Override
    public void update(Notification notification) {
        if (notification.getUserId() == managerId && notification.getType().equals("Gestionnaire")) {
            strategy.send(notification);
        }
    }
}
