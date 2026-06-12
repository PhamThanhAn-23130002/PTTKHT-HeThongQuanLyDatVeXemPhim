package View;

import Controller.GiaVeController;
import Controller.SoatVeController;
import Controller.ThongKeController;

import java.io.Serializable;
import java.util.Scanner;

public class OperationView {
    private Scanner scanner;
    private SoatVeController soatVeController;
    private ThongKeController thongKeController;
    private GiaVeController giaVeController;

    public OperationView(Scanner scanner, SoatVeController soatVeController, ThongKeController thongKeController, GiaVeController giaVeController) {
        this.scanner = scanner;
        this.soatVeController = soatVeController;
        this.thongKeController = thongKeController;
        this.giaVeController = giaVeController;
    }

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
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        thucHienSoatVe();
                        break;
                    case 2:
                        hienThiThongKe();
                        break;
                    case 3:
                        thietLapGiaVe();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên!");
            }
        }
    }

            public void thucHienSoatVe() {
                System.out.println("\n--- CHỨC NĂNG SOÁT VÉ ---");
                System.out.print("Nhập mã vé hoặc QR Code cần soát: ");
                String maVe = scanner.nextLine();
                boolean isValid = soatVeController.verifyTicketCode(maVe);
                if (isValid) {
                    System.out.println("SOÁT VÉ THÀNH CÔNG: Vé hợp lệ! Trạng thái đã chuyển sang 'Đã sử dụng'.");
                } else {
                    System.out.println("LỖI: Vé giả, không tồn tại hoặc đã được quét trước đó!");
                }
            }

            public void hienThiThongKe() {
                System.out.println("\n--- THỐNG KÊ DOANH THU ---");
                System.out.print("Nhập ngày muốn thống kê : ");
                String thoiGian = scanner.nextLine();
                double tongDoanhThu = thongKeController.calculateRevenue(thoiGian);
                int tongSoVe = thongKeController.countTicketsSold(thoiGian);

                if (tongSoVe > 0) {
                    System.out.println("KẾT QUẢ THỐNG KÊ (" + thoiGian + "):");
                    System.out.println("- Tổng số vé bán ra : " + tongSoVe + " vé");
                    System.out.println("- Tổng doanh thu    : " + String.format("%,.0f", tongDoanhThu) + " VNĐ");
                } else {
                    System.out.println("Không có dữ liệu bán vé cho thời gian này.");
                }
            }

            private void thietLapGiaVe() {
                System.out.println("\n--- THIẾT LẬP GIÁ VÉ ĐỘNG ---");
                try {
                    System.out.print("Tên quy tắc: ");
                    String ruleName = scanner.nextLine();

                    System.out.print("Khung giờ/Ngày bắt đầu: ");
                    String startTime = scanner.nextLine();

                    System.out.print("Khung giờ/Ngày kết thúc: ");
                    String endTime = scanner.nextLine();

                    System.out.print("Hệ số nhân gi: ");
                    double multiplier = Double.parseDouble(scanner.nextLine());
                    boolean isAdded = giaVeController.addPricingRule(ruleName, startTime, endTime, multiplier);

                    if (isAdded) {
                        System.out.println("Thiết lập giá vé thành công!");
                    } else {
                        System.out.println("Lỗi: Thời gian thiết lập bị trùng lặp với quy tắc khác.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Hệ số nhân giá phải là một số thực!");
                }
            }
}
