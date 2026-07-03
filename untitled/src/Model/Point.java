package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Point {
    private String id;
    private LocalDateTime date;
    private int pointsEarned;
    private String orderId;
    private String userId;

    private static List<Point> points = new ArrayList<>();
    // BR3.1: Quy tắc tính điểm - 10,000 VND giá trị giao dịch = 1 điểm.
    private static final double POINT_RATE_VND = 10000.0;

    static {
        // OD001 đã PAID + đã tích điểm trước đó (12 điểm).
        points.add(new Point("P001", LocalDateTime.of(2026, 6, 20, 19, 31),
                12, "OD001", "U002"));
    }

    public Point() {}

    public Point(String id, LocalDateTime date, int pointsEarned,
                 String orderId, String userId) {
        this.id = id;
        this.date = date;
        this.pointsEarned = pointsEarned;
        this.orderId = orderId;
        this.userId = userId;
    }

    // UC-3.1 (Sequence): Controller -> Point.verifyTransaction(orderId)
    // Kiểm tra Order tồn tại + status=PAID + chưa từng tích điểm.
    // Throw TRANSACTION_FAILED nếu không hợp lệ.
    public static boolean verifyTransaction(String orderId) {
        Order order = Order.findOrderById(orderId);
        if (order == null || !"PAID".equalsIgnoreCase(order.getStatus())) {
            throw new RuntimeException("TRANSACTION_FAILED");
        }
        if (isAwarded(orderId)) {
            throw new RuntimeException("TRANSACTION_FAILED");
        }
        return true;
    }

    // UC-3.1 (Sequence): Controller -> Point.addPoints(userId, orderId)
    // Tạo Point + cộng totalPoints cho Account. Throw POINT_UPDATE_ERROR nếu lỗi.
    public static int[] addPoints(String userId, String orderId) {
        Account acc = Account.findById(userId);
        Order order = Order.findOrderById(orderId);
        if (acc == null || order == null) {
            throw new RuntimeException("POINT_UPDATE_ERROR");
        }

        int earned = (int) Math.floor(order.calculateTotal() / POINT_RATE_VND);
        // BR3.1-2: điểm không được âm.
        if (earned < 0) {
            throw new RuntimeException("POINT_UPDATE_ERROR");
        }

        String newId = String.format("P%03d", points.size() + 1);
        points.add(new Point(newId, LocalDateTime.now(), earned, orderId, userId));

        double newTotal = acc.getTotalPoints() + earned;
        acc.setTotalPoints(newTotal);
        return new int[] { earned, (int) newTotal };
    }

    public static int getTotalPoints(String userId) {
        Account acc = Account.findById(userId);
        if (acc == null) return 0;
        return (int) acc.getTotalPoints();
    }

    // Helper: order đã được tích điểm chưa (dùng cho UC-3.1 view + verifyTransaction).
    public static boolean isAwarded(String orderId) {
        if (orderId == null) return false;
        for (Point p : points) {
            if (orderId.equalsIgnoreCase(p.orderId)) return true;
        }
        return false;
    }

    public static List<Point> findByUserId(String userId) {
        List<Point> result = new ArrayList<>();
        if (userId == null) return result;
        for (Point p : points) {
            if (userId.equals(p.userId)) result.add(p);
        }
        return result;
    }

    public String getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public int getPointsEarned() { return pointsEarned; }
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
}
