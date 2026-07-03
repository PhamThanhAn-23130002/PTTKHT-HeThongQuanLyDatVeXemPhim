package Model;
import java.util.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private Ticket ticket;
    private int numSeats;
    private List<Payment> paymentList;
    private String status;
    private String accountId;
    private String screeningId;

    private static List<Order> orders = new ArrayList<>();

    public Order() {
        this.paymentList = new ArrayList<>();
    }

    public Order(String id) {
        this.id = id;
        this.paymentList = new ArrayList<>();
    }

    public Order(String id, Ticket ticket, int numSeats, String status,
                 String accountId, String screeningId) {
        this.id = id;
        this.ticket = ticket;
        this.numSeats = numSeats;
        this.status = status;
        this.accountId = accountId;
        this.screeningId = screeningId;
        this.paymentList = new ArrayList<>();
    }

    static {
        // ----- Dữ liệu suất chiếu mẫu cho UC-2.1 (auto-register vào MovieScreening/Movie). -----
        List<Seat> seats1 = new ArrayList<>();
        seats1.add(new Seat("A1", "Thường", "Đã bán"));
        seats1.add(new Seat("A2", "Thường", "Đã bán"));
        Room room1 = new Room("R01", seats1, true);
        MovieScreening sc101 = new MovieScreening("SC101", "20:00 - 24/05/2026", room1);
        List<MovieScreening> scList1 = new ArrayList<>();
        scList1.add(sc101);
        new Movie("M101", "Lật Mặt 8", "Hành động", 120, "Đang chiếu", scList1);

        List<Seat> seats2 = new ArrayList<>();
        seats2.add(new Seat("B3", "VIP", "Đã bán"));
        Room room2 = new Room("R02", seats2, true);
        MovieScreening sc102 = new MovieScreening("SC102", "18:30 - 26/03/2026", room2);
        List<MovieScreening> scList2 = new ArrayList<>();
        scList2.add(sc102);
        new Movie("M102", "Avengers: Doomsday", "Hành động", 150, "Đang chiếu", scList2);

        List<Seat> seats3 = new ArrayList<>();
        seats3.add(new Seat("C5", "Thường", "Đã bán"));
        Room room3 = new Room("R03", seats3, true);
        MovieScreening sc103 = new MovieScreening("SC103", "21:15 - 27/01/2026", room3);
        List<MovieScreening> scList3 = new ArrayList<>();
        scList3.add(sc103);
        new Movie("M103", "Mai 2", "Tâm lý", 130, "Sắp chiếu", scList3);

//        // ----- Các đơn mẫu cho U002 (user1) -----
//        Order od1 = new Order("OD001",
//                new BasicTicket("T001", "A1", "R01", "QR-OD001", false),
//                2, "PAID", "U002", "SC101");
//        od1.addPayment(new Payment("PM001", 120000,
//                LocalDateTime.of(2026, 6, 20, 19, 30), "SUCCESS", null));
//        orders.add(od1);
//
//        Order od2 = new Order("OD002",
//                new BasicTicket("T002", "B3", "R02", "QR-OD002", false),
//                1, "PAID", "U002", "SC102");
//        od2.addPayment(new Payment("PM002", 80000,
//                LocalDateTime.of(2026, 6, 22, 10, 5), "SUCCESS", null));
//        orders.add(od2);
//
//        Order od3 = new Order("OD003",
//                new Student_OldTicket("T003", "C5", "R03", "QR-OD003", false),
//                1, "CANCELED", "U002", "SC103");
//        od3.addPayment(new Payment("PM003", 45000,
//                LocalDateTime.of(2026, 6, 23, 14, 0), "REFUNDED", null));
//        orders.add(od3);
    }



    // Tổng tiền dựa trên danh sách payment, fallback ticket.cost() * numSeats.
    public double calculateTotal() {
        if (paymentList != null && !paymentList.isEmpty()) {
            double sum = 0;
            for (Payment p : paymentList) {
                sum += p.getAmount();
            }
            return sum;
        }
        return ticket == null ? 0 : ticket.cost() * numSeats;
    }

    public void addPayment(Payment payment) {
        if (paymentList == null) paymentList = new ArrayList<>();
        paymentList.add(payment);
    }

    public void updateOrderStatus(String status) {
        this.status = status;
    }

    // UC-2.1 (Sequence): Controller -> Order.findOrdersByAccountId(accountId).
    // BR2.1-1: chỉ trả về order thuộc chính account.
    public static List<Order> findOrdersByAccountId(String accountId) {
        List<Order> result = new ArrayList<>();
        if (accountId == null) return result;
        for (Order o : orders) {
            if (accountId.equals(o.accountId)) result.add(o);
        }
        return result;
    }

    // UC-2.1 (Sequence): Controller -> Order.findOrderById(orderId).
    public static Order findOrderById(String orderId) {
        if (orderId == null) return null;
        for (Order o : orders) {
            if (orderId.equalsIgnoreCase(o.id)) return o;
        }
        return null;
    }

    public static List<Order> getAllOrders() { return orders; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public int getNumSeats() { return numSeats; }
    public void setNumSeats(int numSeats) { this.numSeats = numSeats; }

    public List<Payment> getPaymentList() { return paymentList; }
    public void setPaymentList(List<Payment> paymentList) { this.paymentList = paymentList; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getScreeningId() { return screeningId; }
    public void setScreeningId(String screeningId) { this.screeningId = screeningId; }
}
