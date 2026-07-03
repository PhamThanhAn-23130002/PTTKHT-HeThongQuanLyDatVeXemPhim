package Model;
import java.util.Date;

public class Voucher {
    private Date expDate;
    private String code;
    private double discountValue;
    private int quantity;

    public Voucher(String code, double discountValue, int quantity, Date expDate) {
        this.code = code;
        this.discountValue = discountValue;
        this.quantity = quantity;
        this.expDate = expDate;
    }

    public void decreaseQuantity() { if (quantity > 0) quantity--; }

    public String getCode() { return code; }
    public double getDiscountValue() { return discountValue; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
}