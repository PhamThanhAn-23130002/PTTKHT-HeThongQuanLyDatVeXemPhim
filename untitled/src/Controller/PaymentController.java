package Controller;

import Model.Payment;
import Model.Voucher;

public class PaymentController {

    public double tinhTongThanhToan(double giaVe, Voucher voucher) {
        double tongTien = giaVe;
        if (voucher != null) {
            tongTien -= voucher.getDiscountValue();
        }
        return Math.max(tongTien, 0); // Đảm bảo tiền không âm
    }

    public void thucHienThanhToan(Payment payment) {
        System.out.println("Đang xử lý thanh toán cho mã: " + payment.getPaymentId());
    }
}