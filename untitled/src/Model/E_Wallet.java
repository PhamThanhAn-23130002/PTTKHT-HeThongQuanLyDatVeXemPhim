package Model;

public class E_Wallet implements PaymentMethod {
    @Override
    public boolean checkout(double money) {
        return true;
    }
}