package View;

import java.util.Scanner;

public class OperationView {
    private Scanner scanner;

    public OperationView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayOperationMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n------------------------------------------");
            System.out.println("QUẢN LÝ VẬN HÀNH");
            System.out.println("------------------------------------------");
            System.out.println("1. Soát vé (Quét mã QR / Nhập thủ công)");
            System.out.println("2. Xem Thống kê doanh thu");
            System.out.println("3. Thiết lập giá vé động (Giờ vàng, Lễ/Tết)");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Nhập lựa chọn: ");

            try {
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên!");
            }
        }
    }

}
