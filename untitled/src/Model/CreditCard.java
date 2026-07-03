package Model;

public class CreditCard implements PaymentMethod {
    @Override
    public boolean checkout(double money) {
        return true;
    }
}