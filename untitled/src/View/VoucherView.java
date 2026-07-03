package View;

import Controller.VoucherController;
import Model.Voucher;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VoucherView {
    private final Scanner scanner;
    private final VoucherController voucherController;

    public VoucherView(Scanner scanner, VoucherController voucherController) {
        this.scanner = scanner;
        this.voucherController = voucherController;
    }

    public void displayVoucherMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n==========================================");
            System.out.println("            QUẢN LÝ VOUCHER               ");
            System.out.println("==========================================");
            System.out.println("1. Xem danh sách Voucher");
            System.out.println("2. Thêm Voucher mới");
            System.out.println("3. Cập nhật Voucher (Sửa giá trị/số lượng)");
            System.out.println("4. Xóa Voucher");
            System.out.println("0. Thoát và quay lại Menu Chính");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        xemDanhSach();
                        break;
                    case 2:
                        themVoucher();
                        break;
                    case 3:
                        suaVoucher();
                        break;
                    case 4:
                        xoaVoucher();
                        break;
                    case 0:
                        System.out.println(">> Đã thoát Quản lý Voucher.");
                        break;
                    default:
                        System.out.println(">> Lỗi: Vui lòng nhập từ 0 đến 4!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Lỗi: Bạn phải nhập một số nguyên!");
            }
        }
    }

    private void xemDanhSach() {
        List<Voucher> list = voucherController.getAllVouchers();
        if (list.isEmpty()) {
            System.out.println(">> Danh sách Voucher đang trống.");
            return;
        }
        System.out.println("--- DANH SÁCH VOUCHER ---");
        for (Voucher v : list) {
            System.out.println("Mã: " + v.getCode() + " | Giảm: " + v.getDiscountValue() + " | SL: " + v.getQuantity());
        }
    }

    private void themVoucher() {
        System.out.print("Nhập mã Voucher mới: ");
        String code = scanner.nextLine();

        if (voucherController.findVoucherByCode(code) != null) {
            System.out.println(">> Mã này đã tồn tại!");
            return;
        }

        try {
            System.out.print("Nhập giá trị giảm: ");
            double discount = Double.parseDouble(scanner.nextLine());
            System.out.print("Nhập số lượng: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Voucher newVoucher = new Voucher(code, discount, quantity, new Date());
            voucherController.addVoucher(newVoucher);
            System.out.println(">> Thêm Voucher thành công!");
        } catch (NumberFormatException e) {
            System.out.println(">> Lỗi: Giá trị hoặc số lượng phải là số!");
        }
    }

    private void suaVoucher() {
        System.out.print("Nhập mã Voucher cần sửa: ");
        String code = scanner.nextLine();

        try {
            System.out.print("Nhập giá trị giảm MỚI: ");
            double discount = Double.parseDouble(scanner.nextLine());
            System.out.print("Nhập số lượng MỚI: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (voucherController.updateVoucher(code, discount, quantity)) {
                System.out.println(">> Cập nhật thành công!");
            } else {
                System.out.println(">> Không tìm thấy mã Voucher này.");
            }
        } catch (NumberFormatException e) {
            System.out.println(">> Lỗi: Dữ liệu nhập vào phải là số!");
        }
    }

    private void xoaVoucher() {
        System.out.print("Nhập mã Voucher cần xóa: ");
        String code = scanner.nextLine();
        if (voucherController.deleteVoucher(code)) {
            System.out.println(">> Đã xóa Voucher thành công!");
        } else {
            System.out.println(">> Không tìm thấy mã Voucher này.");
        }
    }
}