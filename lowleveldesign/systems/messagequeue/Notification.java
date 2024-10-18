package lowleveldesign.systems.messagequeue;

public class Notification {
    private String userId;
    private String userName;
    private NotificationType type;
    private String message;

    public Notification(String id, String name, String message, NotificationType type) {
        this.userId = id;
        this.userName = name;
        this.message = message;
        this.type = type;

    }

    public NotificationType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
