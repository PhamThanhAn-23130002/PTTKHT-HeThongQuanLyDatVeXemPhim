package View;

import Controller.GiaVeController;
import Controller.SoatVeController;
import Controller.ThongKeController;

import java.io.Serializable;
import java.util.Scanner;

public class OperationView {
    private Scanner scanner;
    private SoatVeController soatVeController = new SoatVeController();
    private ThongKeController thongKeController = new ThongKeController();
    private GiaVeController giaVeController = new GiaVeController();

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

    private void thucHienSoatVe() {
        System.out.println("\n--- MÀN HÌNH SOÁT VÉ ---");
        System.out.println("1. Quét mã QR trên vé");
        System.out.println("2. Nhập mã vé thủ công");
        System.out.print("Chọn phương thức: ");
        String luaChon = scanner.nextLine();
        System.out.print("Nhập mã (qrcodeData): ");
        String qrcodeData = scanner.nextLine();

        boolean result = false;
        if (luaChon.equals("1")) {
            result = soatVeController.verifyQrCode(qrcodeData);
        } else {
            result = soatVeController.verifyTicketCode(qrcodeData);
        }

        if (result) {
            System.out.println("Soát vé thành công: Vé hợp lệ và chưa sử dụng!");
        } else {
            System.out.println("Cảnh báo: Vé không tồn tại hoặc đã qua sử dụng!");
        }
    }

    private void hienThiThongKe() {
        System.out.println("\n--- DASHBOARD THỐNG KÊ ---");
        System.out.print("Nhập bộ lọc (filterCriteria - Phim/Ngày/Tháng): ");
        String filterCriteria = scanner.nextLine();
        Object chartData = thongKeController.getStatisticData(filterCriteria);

        if (chartData == null) {
            System.out.println("Thông báo: Không có dữ liệu phù hợp với bộ lọc.");
        } else {
            System.out.println("Hiển thị biểu đồ thống kê(Dữ liệu: " + chartData + ")");
            System.out.print("Bạn có muốn xuất báo cáo không? (Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                System.out.print("Chọn định dạng (PDF/Excel): ");
                String formatType = scanner.nextLine();

                String fileDownloadLink = thongKeController.exportReport(chartData, formatType);
                System.out.println("Đã tải báo cáo về máy: " + fileDownloadLink);
            }
        }
    }

    private void thietLapGiaVe() {
        System.out.println("\n--- MÀN HÌNH THIẾT LẬP GIÁ VÉ ---");
        System.out.println("Nhập dữ liệu thiết lập (priceData) bao gồm Loại giá, Thời gian...");
        System.out.print("Tên cấu hình giá (Ví dụ: Gia_Le_30_4): ");
        String priceData = scanner.nextLine();
        boolean isSuccess = giaVeController.savePriceConfig(priceData);

        if (isSuccess) {
            System.out.println("Thông báo: Thiết lập giá thành công!");
        } else {
            System.out.println("Lỗi: Trùng lặp thời gian với cấu hình giá khác!");
        }
    }
}
