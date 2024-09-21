package lowleveldesign.hotelmanagement;

public class CashPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {

        System.out.println("Payment done via cash");

        return true;
    }
}
