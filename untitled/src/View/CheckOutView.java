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

    public void displayCheckOutMenu(Order order) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("==========================================");
            System.out.println("          XÁC NHẬN VÀ THANH TOÁN          ");
            System.out.println("==========================================");

            System.out.print("Nhập mã Voucher (Enter để bỏ qua): ");
            String vCode = scanner.nextLine();
            Voucher v = voucherController.findVoucherByCode(vCode);

            double total = checkoutController.calculateTotalAmount(order, v);
            System.out.println("Tổng tiền thanh toán: " + total);

            System.out.println("1. Thanh toán MoMo | 2. Thanh toán Visa");
            System.out.print("Lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) break;

                PaymentMethod method = (choice == 1) ? new E_Wallet() : new CreditCard();

                if (checkoutController.processPayment(order, v, method)) {
                    System.out.println(">> Giao dịch thành công!");
                    choice = 0;
                } else {
                    System.out.println(">> Giao dịch thất bại!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu!");
            }
        }
    }
}