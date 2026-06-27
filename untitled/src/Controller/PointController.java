package Controller;

import Model.Point;

public class PointController {

    // UC-3.1 (Sequence): View -> Controller.handlePaymentComplete(userId, orderId)
    // Controller -> Point.verifyTransaction -> Point.addPoints.
    // Trả về int[]{ pointsEarned, totalPoints } hoặc null nếu lỗi.
    public int[] handlePaymentComplete(String userId, String orderId) {
        try {
            Point.verifyTransaction(orderId);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return null;
        }
        try {
            return Point.addPoints(userId, orderId);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return null;
        }
    }

    // UC-3.1: lấy tổng điểm hiện tại để hiển thị.
    public int getTotalPoints(String userId) {
        return Point.getTotalPoints(userId);
    }

    private void handleError(String code) {
        switch (code) {
            case "TRANSACTION_FAILED":
                System.out.println("Lỗi: Giao dịch thất bại hoặc đã được tích điểm trước đó, không cộng điểm!");
                break;
            case "POINT_UPDATE_ERROR":
                System.out.println("Lỗi: Cập nhật điểm thất bại, vui lòng thử lại!");
                break;
            default:
                System.out.println("Lỗi: " + code);
        }
    }
}
