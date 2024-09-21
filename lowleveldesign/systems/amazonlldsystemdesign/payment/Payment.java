package lowleveldesign.systems.amazonlldsystemdesign.payment;

// This tells us that process payment for that particular amount, Now underlying this could be procedded by either creditcard etc etc
public interface Payment {
    boolean processPayment(double amount);
}
