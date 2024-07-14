package lowleveldesign.solidprinciples;

// Wrong way
class NotificationService {
    public void sendEmail(String message) {
        // Logic to send an email
    }

    public void sendSMS(String message) {
        // Logic to send an SMS
    }
}

// Correct Way
// Notification.java
interface Notification {
    void send(String message);
}

// EmailNotification.java
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("This is an email notification: " + message);
    }
}

// SMSNotification.java
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("This is an SMS notification: " + message);
    }
}

// OpenClosePrinciple.java
class OpenClosePrinciple {
    public static void main(String[] args) {
        // Create instances of notification types
        Notification emailNotification = new EmailNotification();
        Notification smsNotification = new SMSNotification();

        // Sending notifications
        emailNotification.send("Hello via Email!");
        smsNotification.send("Hello via SMS!");
    }
}

