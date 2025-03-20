package data;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private String message;
    private Date date;
    private int userId;  // ID du client ou du gestionnaire
    private String type; // "Client" ou "Gestionnaire"

    public Notification(String message, int userId, String type) {
        this.message = message;
        this.date = new Date();
        this.userId = userId;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }
}
