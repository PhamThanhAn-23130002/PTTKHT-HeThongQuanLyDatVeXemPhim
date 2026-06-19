package View;

import java.util.List;
import java.util.Scanner;

import Controller.InventoryController;
import Model.InventoryItem;

public class InventoryView {

    // Controller xử lý nghiệp vụ kho
    private InventoryController controller;

    // Dùng để nhập dữ liệu từ bàn phím
    private Scanner scanner;

    public InventoryView(InventoryController inventoryController) {
        this.controller = inventoryController;
        this.scanner = new Scanner(System.in);
    }

    // Hiển thị menu quản lý kho
    public void showMenu() {

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== QUẢN LÝ KHO BẮP NƯỚC =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Cập nhật số lượng");
            System.out.println("3. Xem tồn kho");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addItem();
                    break;

                case 2:
                    updateStock();
                    break;

                case 3:
                    displayInventory(controller.getInventory());
                    break;

                case 0:
                    System.out.println("Quay lại...");
                    break;
            }
        }
    }

    // Thêm sản phẩm mới vào kho
    private void addItem() {

        System.out.print("Mã sản phẩm: ");
        String id = scanner.nextLine();

        System.out.print("Tên sản phẩm: ");
        String name = scanner.nextLine();

        System.out.print("Số lượng: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        InventoryItem item =
                new InventoryItem(id, name, quantity);

        controller.addItem(item);

        System.out.println("Thêm sản phẩm thành công!");
    }

    // Cập nhật lại số lượng tồn kho
    private void updateStock() {

        System.out.print("Mã sản phẩm: ");
        String id = scanner.nextLine();

        System.out.print("Số lượng mới: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        controller.updateStock(id, quantity);

        System.out.println("Cập nhật thành công!");
    }

    // Hiển thị danh sách sản phẩm trong kho
    public void displayInventory(List<InventoryItem> inventory) {

        System.out.println("\n===== KHO BẮP NƯỚC =====");

        for (InventoryItem item : inventory) {

            System.out.println(
                    item.getItemName()
                    + " : "
                    + item.getQuantity());
        }
    }
}