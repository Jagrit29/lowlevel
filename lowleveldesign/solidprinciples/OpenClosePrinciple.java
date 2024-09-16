package lowleveldesign.solidprinciples;

/*
Open/Closed Principle (OCP):
Definition: Classes should be open for extension but closed for modification.
Explanation: You should be able to add new functionality to a class by extending it without changing its existing code.
Example: If you have a Shape class, you can add new shapes (like Circle or Rectangle) by extending Shape, without modifying the Shape class itself.
*/

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

