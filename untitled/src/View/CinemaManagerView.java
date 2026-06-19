package View;

import java.util.Scanner;

import Controller.MovieController;
import Controller.InventoryController;

public class CinemaManagerView {

    private Scanner scanner;

    private MovieView movieView;
    private InventoryView inventoryView;

    public CinemaManagerView(Scanner scanner) {

        this.scanner = scanner;

        MovieController movieController =
                new MovieController();

        InventoryController inventoryController =
                new InventoryController();

        movieView = new MovieView(movieController);
        inventoryView = new InventoryView(inventoryController);
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

                switch (choice) {

                    case 1:
                        movieView.showMenu();
                        break;

                    case 3:
                        movieView.updateStatusMenu();
                        break;

                    case 5:
                        inventoryView.showMenu();
                        break;

                    case 0:
                        System.out.println("Quay lại Menu Chính...");
                        break;
                }

            } catch (NumberFormatException e) {

                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }
}