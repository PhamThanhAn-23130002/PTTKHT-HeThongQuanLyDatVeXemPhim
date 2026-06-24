package View;

import Controller.BookingController;
import Model.Movie;
import Model.MovieScreening;
import Model.Seat;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookingView {
    private Scanner scanner;
    private BookingController bookingController;

    public BookingView(Scanner scanner) {
        this.scanner = scanner;
        this.bookingController = new BookingController();
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
                switch (choice) {
                    case 1:
                        booking();
                        break;
                    case 2:
                        System.out.println(">> Đang chuyển sang bước Thanh toán...");
                        break;
                    case 0:
                        System.out.println("Quay lại màn hình chính...");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }

    private void booking() {
        System.out.println("\n--- DANH SÁCH PHIM ĐANG CHIẾU ---");
        List<Movie> movies = bookingController.getActiveMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getMovieName() + " (" + movies.get(i).getDuration() + ")");
        }
        System.out.print("Chọn phim (nhập STT): ");
        int movieChoice = Integer.parseInt(scanner.nextLine());
        Movie selectedMovie = movies.get(movieChoice - 1);

        System.out.println("\n--- CÁC SUẤT CHIẾU: " + selectedMovie.getMovieName() + " ---");
        List<MovieScreening> screenings = bookingController.getScreeningsByMovie(selectedMovie.getMovieId());
        for (int i = 0; i < screenings.size(); i++) {
            System.out.println((i + 1) + ". Giờ chiếu: " + screenings.get(i).getDate() + " - Phòng: " + screenings.get(i).getRoom().getId());
        }
        System.out.print("Chọn suất chiếu: ");
        int screeningChoice = Integer.parseInt(scanner.nextLine());
        MovieScreening selectedScreening = screenings.get(screeningChoice - 1);

        System.out.println("\n--- SƠ ĐỒ GHẾ ---");
        bookingController.connectWebSocket(selectedScreening.getId());
        List<Seat> seats = bookingController.getSeatMap(selectedScreening.getId());
        System.out.print("Màn hình: ");
        for (Seat seat : seats) {
            System.out.print("[" + seat.getId() + ": " + seat.getStatus() + "]  ");
        }
        System.out.println();

        System.out.print("\nNhập mã ghế bạn muốn đặt (cách nhau bởi dấu phẩy, VD: A1,A2): ");
        String seatInput = scanner.nextLine();
        List<String> selectedSeatIds = Arrays.asList(seatInput.split(","));

        System.out.println("\n--- ĐANG XỬ LÝ KHÓA GHẾ ---");
        boolean holdSuccess = bookingController.holdSeatsAndStartTimer(selectedSeatIds);

        if (holdSuccess) {
            System.out.println("Thành công: Cập nhật ghế sang [Đang giữ]. Đồng hồ đếm ngược 10:00 đã kích hoạt!");
            Object donHang = bookingController.createOrder(selectedSeatIds);
            System.out.println("Hệ thống: Đã khởi tạo đơn hàng .");
            System.out.println(">> Bạn đã hoàn thành chọn vé. Sẵn sàng chuyển hướng sang bước Thanh Toán!");
            CheckOutView thanhToanView = new CheckOutView(scanner);
            thanhToanView.displayCheckOutMenu(donHang);
        } else {
            System.out.println("Thất bại: Xung đột dữ liệu! Ghế bạn chọn không hợp lệ hoặc vừa bị người khác mua mất.");
        }
    }
}
