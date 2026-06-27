package View;

import Controller.PointController;
import Model.Account;
import Model.Movie;
import Model.Order;
import Model.Point;

import java.util.List;
import java.util.Scanner;

public class PointView {
    private Scanner scanner;
    private PointController pointController;

    public PointView(Scanner scanner, PointController pointController) {
        this.scanner = scanner;
        this.pointController = pointController;
    }

    public void displayPointMenu(Account currentUser) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n------------------------------------------");
            System.out.println("           TÍCH ĐIỂM        ");
            System.out.println("------------------------------------------");
            System.out.println("Tổng điểm hiện tại: " + pointController.getTotalPoints(currentUser.getId()));
            System.out.println("1. Tích điểm cho 1 đơn đã thanh toán");
            System.out.println("2. Xem các đơn chưa được tích điểm");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn: ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        xuLyTichDiem(currentUser);
                        break;
                    case 2:
                        hienThiDonChuaTich(currentUser);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }

    private void hienThiDonChuaTich(Account currentUser) {
        List<Order> all = Order.findOrdersByAccountId(currentUser.getId());
        System.out.println("\n--- Đơn đã thanh toán, chưa tích điểm ---");
        boolean any = false;
        for (Order o : all) {
            if ("PAID".equalsIgnoreCase(o.getStatus()) && !Point.isAwarded(o.getId())) {
                Movie m = Movie.findByScreeningId(o.getScreeningId());
                String movieName = (m == null) ? "(không rõ)" : m.getMovieName();
                System.out.println("- " + o.getId() + " | " + movieName
                        + " | Tổng: " + (long) o.calculateTotal() + " VND");
                any = true;
            }
        }
        if (!any) System.out.println("(Không có đơn nào)");
    }

    private void xuLyTichDiem(Account currentUser) {
        System.out.print("Nhập mã đơn cần tích điểm (vd: OD002): ");
        String orderId = scanner.nextLine().trim();
        int[] result = pointController.handlePaymentComplete(currentUser.getId(), orderId);
        if (result != null) {
            renderPointResult(result[0], result[1]);
        }
    }

    // UC-3.1 (Sequence): Controller -> PointView.renderPointResult(pointsEarned, totalPoints)
    public void renderPointResult(int pointsEarned, int totalPoints) {
        System.out.println("\n=== TÍCH ĐIỂM THÀNH CÔNG ===");
        System.out.println("Điểm nhận được     : " + pointsEarned);
        System.out.println("Tổng điểm hiện tại : " + totalPoints);
    }

    public void showError(String message) {
        System.out.println("[LỖI] " + message);
    }
}
