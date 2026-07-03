package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private List<Movie> listPhim;
    private List<Seat> listGhe;

    public BookingController() {
        listGhe = new ArrayList<>();
        listGhe.add(new Seat("A1", "Thường", "Trống"));
        listGhe.add(new Seat("A2", "Thường", "Trống"));
        listGhe.add(new Seat("V1", "VIP", "Đã bán"));

        Room phong1 = new Room("R01", listGhe, true);

        List<MovieScreening> danhSachSuatChieu = new ArrayList<>();
        danhSachSuatChieu.add(new MovieScreening("SC01", "20:00 - 24/06/2026", phong1));

        listPhim = new ArrayList<>();
        listPhim.add(new Movie("M01", "Lật Mặt 8", "Hành động", 120, "Sắp chiếu", danhSachSuatChieu));
    }

    public List<Movie> getActiveMovies() {
        return listPhim;
    }

    public List<MovieScreening> getScreeningsByMovie(String movieId) {
        return listPhim.get(0).getMovieScreeningList();
    }

    public void connectWebSocket(String screeningId) {
        System.out.println("Đang thiết lập kết nối WebSocket với Server (Mã suất chiếu: " + screeningId + ")... OK!");
    }

    public List<Seat> getSeatMap(String screeningId) {
        return listGhe;
    }

    public boolean holdSeatsAndStartTimer(List<String> seatIds) {
        boolean checkThanhCong = true;
        for (String id : seatIds) {
            for (Seat seat : listGhe) {
                if (seat.getId().equalsIgnoreCase(id.trim())) {
                    if (!seat.markAsHold()) {
                        checkThanhCong = false;
                    }
                }
            }
        }
        return checkThanhCong;
    }

    public Order createOrder(List<String> seatIds) {
        return null;
    }
}