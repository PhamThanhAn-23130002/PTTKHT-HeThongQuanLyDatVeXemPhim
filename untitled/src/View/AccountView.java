package View;

import java.util.Scanner;

public class AccountView {
    private Scanner scanner;

    public AccountView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayAccountMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n------------------------------------------");
            System.out.println("QUẢN LÝ TÀI KHOẢN & KHÁCH HÀNG");
            System.out.println("------------------------------------------");
            System.out.println("1. Đăng nhập / Đăng ký / Phân quyền");
            System.out.println("2. Xem hồ sơ & Lịch sử đặt vé");
            System.out.println("3. Tích điểm & Thăng hạng");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                // Tự viết Switch-Case
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }
}
