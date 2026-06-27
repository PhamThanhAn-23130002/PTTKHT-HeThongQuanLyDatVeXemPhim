package Controller;

import Model.Reward;
import java.util.List;

public class RewardController {

    // UC-3.2 (Sequence): View -> Controller.getAvailableRewards()
    public List<Reward> getAvailableRewards() {
        return Reward.getAvailableRewards();
    }

    // UC-3.2 (Sequence): View -> Controller.checkPoints(userId, rewardId)
    // Trả về Reward khi đủ điểm; null nếu lỗi (đã in thông báo).
    public Reward checkPoints(String userId, String rewardId) {
        try {
            Reward.checkPoints(userId, rewardId);
            return Reward.findRewardById(rewardId);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return null;
        }
    }

    // UC-3.2 (Sequence): View -> Controller.redeemReward(userId, rewardId)
    public boolean redeemReward(String userId, String rewardId) {
        try {
            return Reward.redeemReward(userId, rewardId);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return false;
        }
    }

    private void handleError(String code) {
        switch (code) {
            case "INSUFFICIENT_POINTS":
                System.out.println("Lỗi: Không đủ điểm để đổi quà này!");
                break;
            case "REWARD_NOT_FOUND":
                System.out.println("Lỗi: Phần quà không tồn tại!");
                break;
            case "REWARD_OUT_OF_STOCK":
                System.out.println("Lỗi: Phần quà đã hết hàng!");
                break;
            case "REDEEM_ERROR":
                System.out.println("Lỗi: Đổi quà thất bại, vui lòng thử lại!");
                break;
            default:
                System.out.println("Lỗi: " + code);
        }
    }
}
