package Controller;

import Model.Rank;

public class RankController {

    // UC-3.3 (Sequence): View -> Controller.handleCheckRank(userId)
    // Controller -> Rank.checkRankEligibility -> Rank.upgradeRank.
    // Trả về tên hạng mới khi thành công; null nếu lỗi/không đủ điều kiện.
    public String handleCheckRank(String userId) {
        String newRank;
        try {
            newRank = Rank.checkRankEligibility(userId);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return null;
        }
        try {
            Rank.upgradeRank(userId, newRank);
            return newRank;
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return null;
        }
    }

    private void handleError(String code) {
        switch (code) {
            case "NOT_ELIGIBLE":
                System.out.println("Thông báo: Chưa đủ điều kiện thăng hạng, giữ nguyên hạng hiện tại!");
                break;
            case "RANK_UPDATE_ERROR":
                System.out.println("Lỗi: Cập nhật hạng thất bại, vui lòng thử lại!");
                break;
            default:
                System.out.println("Lỗi: " + code);
        }
    }
}
