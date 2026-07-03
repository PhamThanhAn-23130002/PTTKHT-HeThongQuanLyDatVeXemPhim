package View;

import Controller.BookingHistoryController;
import Model.Account;
import Model.Movie;
import Model.MovieScreening;
import Model.Order;
import Model.Ticket;

import java.util.List;
import java.util.Scanner;

public class BookingHistoryView {
    private Scanner scanner;
    private BookingHistoryController bookingHistoryController;

    public BookingHistoryView(Scanner scanner, BookingHistoryController bookingHistoryController) {
        this.scanner = scanner;
        this.bookingHistoryController = bookingHistoryController;
    }

    // UC-2.1 (Sequence): View -> Controller.findOrdersByAccountId(accountId).
    public void handleViewHistory(Account currentUser) {
        List<Order> bookings = bookingHistoryController.findOrdersByAccountId(currentUser.getId());
        if (bookings.isEmpty()) {
            showMessage("Chưa có lịch sử đặt vé");
            return;
        }
        renderBookingList(bookings);

        System.out.print("\nNhập mã đơn để xem chi tiết (vd OD001, 0 = quay lại): ");
        String choice = scanner.nextLine().trim();
        if (choice.equals("0") || choice.isEmpty()) return;

        Order detail = bookingHistoryController.findOrderById(choice);
        if (detail == null || !detail.getAccountId().equals(currentUser.getId())) {
            // BR2.1-1: chặn xem đơn của người khác.
            System.out.println("[LỖI] Không tìm thấy đơn hoặc bạn không có quyền xem.");
            return;
        }
        renderBookingDetail(detail);
    }

    // UC-2.1 (Sequence): Controller -> BookingHistoryView.renderBookingList(bookings).
    public void renderBookingList(List<Order> bookings) {
        System.out.println("\n========================= LỊCH SỬ ĐẶT VÉ =========================");
        System.out.println(String.format("%-4s %-7s %-22s %-20s %-7s %-5s %-12s %-10s",
                "STT", "Mã đơn", "Tên phim", "Ngày/giờ chiếu", "Phòng", "Ghế", "Trạng thái", "Tổng tiền"));
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < bookings.size(); i++) {
            Order o = bookings.get(i);
            MovieScreening sc = MovieScreening.findById(o.getScreeningId());
            Movie movie = Movie.findByScreeningId(o.getScreeningId());
            Ticket t = o.getTicket();

            String movieName = movie == null ? "(không rõ)" : movie.getMovieName();
            String date = sc == null ? "-" : sc.getDate();
            String roomId = (sc == null || sc.getRoom() == null) ? "-" : sc.getRoom().getId();
            String seatId = t == null ? "-" : t.getSeatID();
            String status = o.getStatus();
            String total = String.format("%,d", (long) o.calculateTotal());

            System.out.println(String.format("%-4d %-7s %-22s %-20s %-7s %-5s %-12s %-10s",
                    i + 1, o.getId(), movieName, date, roomId, seatId, status, total));
        }
    }

    // UC-2.1 (Sequence - Optional flow): renderBookingDetail.
    public void renderBookingDetail(Order o) {
        MovieScreening sc = MovieScreening.findById(o.getScreeningId());
        Movie movie = Movie.findByScreeningId(o.getScreeningId());
        Ticket t = o.getTicket();

        System.out.println("\n=================== CHI TIẾT VÉ ===================");
        System.out.println("Mã đơn       : " + o.getId());
        System.out.println("Tên phim     : " + (movie == null ? "(không rõ)" : movie.getMovieName()));
        System.out.println("Giờ chiếu    : " + (sc == null ? "-" : sc.getDate()));
        System.out.println("Phòng        : " + (sc == null || sc.getRoom() == null ? "-" : sc.getRoom().getId()));
        System.out.println("Ghế ngồi     : " + (t == null ? "-" : t.getSeatID()));
        System.out.println("Loại vé      : " + (t == null ? "-" : t.getType()));
        System.out.println("Số vé        : " + o.getNumSeats());
        System.out.println("Trạng thái   : " + o.getStatus());
        System.out.println("Tổng tiền    : " + String.format("%,d", (long) o.calculateTotal()) + " VND");
    }

    public void showMessage(String msg) {
        System.out.println("[THÔNG BÁO] " + msg);
    }
}
