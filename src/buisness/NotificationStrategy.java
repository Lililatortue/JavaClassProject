package buisness;

import data.Notification;

public interface NotificationStrategy {
    void send(Notification notification);
}
