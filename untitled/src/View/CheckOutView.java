package View;

import Controller.CheckoutController;
import Controller.VoucherController;
import Model.*;
import java.util.Scanner;

public class CheckOutView {
    private final Scanner scanner;
    private final CheckoutController checkoutController = new CheckoutController();
    private final VoucherController voucherController = new VoucherController();

    public CheckOutView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayCheckOutMenu(Order donHang) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("==========================================");
            System.out.println("          XÁC NHẬN VÀ THANH TOÁN          ");
            System.out.println("==========================================");
            System.out.print("Nhập mã Voucher (Enter để bỏ qua): ");
            String vCode = scanner.nextLine();
            Voucher v = voucherController.findVoucherByCode(vCode);

            double total = checkoutController.calculateTotalAmount(donHang, v);
            System.out.println("Tổng tiền thanh toán: " + total);

            System.out.println("1. Thanh toán bằng MoMo (E_Wallet)");
            System.out.println("2. Thanh toán bằng Thẻ Visa (CreditCard)");
            System.out.println("0. Hủy giao dịch và quay lại");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    System.out.println(">> Đã hủy giao dịch.");
                    break;
                }

                PaymentMethod method = null;

                switch (choice) {
                    case 1:
                        method = new E_Wallet();
                        break;
                    case 2:
                        method = new CreditCard();
                        break;
                    default:
                        System.out.println(">> Lựa chọn không hợp lệ! Vui lòng chọn 1 hoặc 2.");
                        continue;
                }

                if (checkoutController.processPayment(donHang, v, method)) {
                    System.out.println(">> Đang xử lý giao dịch... THÀNH CÔNG! Đã xuất vé.");
                    choice = 0;
                } else {
                    System.out.println(">> Giao dịch thất bại! Vui lòng thử lại.");
                }

            } catch (NumberFormatException e) {
                System.out.println(">> Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }
}