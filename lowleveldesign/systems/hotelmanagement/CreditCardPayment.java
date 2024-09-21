package lowleveldesign.systems.hotelmanagement;

public class CreditCardPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment done via creditCard");
        return true;
    }
}
