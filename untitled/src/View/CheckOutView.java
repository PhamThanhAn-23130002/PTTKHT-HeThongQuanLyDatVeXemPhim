package View;

import java.util.Scanner;

public class CheckOutView {
    private Scanner scanner;

    public CheckOutView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayCheckOutMenu(Object draftOrderData) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("==========================================");
            System.out.println("          XÁC NHẬN VÀ THANH TOÁN          ");
            System.out.println("==========================================");

            System.out.println("Hệ thống thanh toán đã nhận được dữ liệu: " + draftOrderData);

            System.out.println("1. Thanh toán bằng MoMo");
            System.out.println("2. Thanh toán bằng Thẻ Visa");
            System.out.print("Nhập lựa chọn: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                // TODO: Logic switch case
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }

        System.out.println(">> Đang xử lý giao dịch... THÀNH CÔNG! Đã xuất vé.");
    }
}