package View;

import java.util.Scanner;

public class BookingView {
    private Scanner scanner;

    public BookingView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayBookingMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n------------------------------------------");
            System.out.println("ĐẶT VÉ & THANH TOÁN");
            System.out.println("------------------------------------------");
            System.out.println("1. Bắt đầu luồng đặt vé");
            System.out.println("2. Áp dụng Voucher & Thanh toán");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                // Viết logic Switch-Case ở đây
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }
}
