package Model;

public class Payment {
    private String id;
    private double amount;
    private boolean status;
    private String date;
    private PaymentMethod paymentMethod;

    public Payment(String id, double amount, PaymentMethod paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = false;
    }

    public boolean executePayment() {
        this.status = paymentMethod.checkout(this.amount);
        return this.status;
    }

    public double getAmount() { return amount; }
    public void setStatus(boolean status) { this.status = status; }

    public String getPaymentId() {
        return this.id;
    }
}