package lowleveldesign.systems.messagequeue;

class NotificationSystemDemo {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        // Start 3 consumers for Email notifications
        service.startConsumers(3, "Email");

        // Start 3 consumers for SMS notifications
        service.startConsumers(3, "SMS");

        // Simulate producers adding notifications to the queue
        service.startProducer("1", "John Doe", "Email", "Your order has been shipped!");
        service.startProducer("2", "Jane Doe", "SMS", "Your OTP code is 1234.");
    }
}