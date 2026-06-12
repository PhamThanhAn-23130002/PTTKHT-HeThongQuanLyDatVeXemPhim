package View;

import java.util.Scanner;

public class CinemaManagerView {
        private Scanner scanner;

        public CinemaManagerView(Scanner scanner) {
            this.scanner = scanner;
        }

        public void displayManagerMenu() {
            int choice = -1;
            while (choice != 0) {
                System.out.println("\n------------------------------------------");
                System.out.println("QUẢN LÝ PHIM, PHÒNG & LỊCH CHIẾU");
                System.out.println("------------------------------------------");
                System.out.println("1. Thêm / Sửa / Xóa phim");
                System.out.println("2. Lập lịch chiếu");
                System.out.println("3. Cập nhật trạng thái phim");
                System.out.println("4. Sơ đồ phòng chiếu");
                System.out.println("5. Quản lý kho bắp nước");
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
