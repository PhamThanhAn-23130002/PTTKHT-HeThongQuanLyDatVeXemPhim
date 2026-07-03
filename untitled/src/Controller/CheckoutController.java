package Controller;

import Model.*;

public class CheckoutController {
    public double calculateTotalAmount(Order order, Voucher v) {
        double total = order.getBaseCost() * order.getNumSeats();
        if (v != null && v.getQuantity() > 0) {
            total -= v.getDiscountValue();
        }
        return Math.max(0, total);
    }

    public boolean processPayment(Order order, Voucher v, PaymentMethod method) {
        double finalAmount = calculateTotalAmount(order, v);
        Payment payment = new Payment("PAY-" + order.getId(), finalAmount, method);

        if (payment.executePayment()) {
            order.updateOrderStatus("Đã thanh toán");
            if (v != null) v.decreaseQuantity();
            return true;
        }
        return false;
    }
}