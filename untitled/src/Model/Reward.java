package Model;

import java.util.ArrayList;
import java.util.List;

public class Reward {
    private String id;
    private String name;
    private int requiredPoints;
    private int quantity;
    private String description;

    private static List<Reward> rewards = new ArrayList<>();

    static {
        rewards.add(new Reward("RW1", "Bắp rang miễn phí", 50, 10,
                "1 phần bắp rang size L"));
        rewards.add(new Reward("RW2", "Nước ngọt miễn phí", 30, 20,
                "1 ly nước ngọt size M"));
        rewards.add(new Reward("RW3", "Vé xem phim miễn phí", 200, 5,
                "1 vé phim 2D bất kỳ"));
        rewards.add(new Reward("RW4", "Combo đôi", 150, 8,
                "2 bắp + 2 nước"));
    }

    public Reward(String id, String name, int requiredPoints, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.requiredPoints = requiredPoints;
        this.quantity = quantity;
        this.description = description;
    }

    // UC-3.2 (Sequence): Controller -> RewardModel.getAvailableRewards()
    // Trả về quà còn quantity > 0.
    public static List<Reward> getAvailableRewards() {
        List<Reward> result = new ArrayList<>();
        for (Reward r : rewards) {
            if (r.quantity > 0) result.add(r);
        }
        return result;
    }

    // UC-3.2 (Sequence): Controller -> RewardModel.findRewardById(rewardId)
    public static Reward findRewardById(String rewardId) {
        if (rewardId == null) return null;
        for (Reward r : rewards) {
            if (rewardId.equalsIgnoreCase(r.id)) return r;
        }
        return null;
    }

    // UC-3.2 (Sequence): Controller -> RewardModel.checkPoints(userId, rewardId)
    // BR3.2-1: phải đủ điểm. Throw INSUFFICIENT_POINTS nếu không đủ.
    public static boolean checkPoints(String userId, String rewardId) {
        Account acc = Account.findById(userId);
        Reward r = findRewardById(rewardId);
        if (acc == null || r == null) {
            throw new RuntimeException("REWARD_NOT_FOUND");
        }
        if (r.quantity <= 0) {
            throw new RuntimeException("REWARD_OUT_OF_STOCK");
        }
        if (acc.getTotalPoints() < r.requiredPoints) {
            throw new RuntimeException("INSUFFICIENT_POINTS");
        }
        return true;
    }

    // UC-3.2 (Sequence): Controller -> RewardModel.redeemReward(userId, rewardId)
    // Trừ điểm + giảm quantity + ghi Redemption. Throw REDEEM_ERROR nếu lỗi.
    public static boolean redeemReward(String userId, String rewardId) {
        Account acc = Account.findById(userId);
        Reward r = findRewardById(rewardId);
        if (acc == null || r == null) {
            throw new RuntimeException("REDEEM_ERROR");
        }
        if (acc.getTotalPoints() < r.requiredPoints || r.quantity <= 0) {
            throw new RuntimeException("REDEEM_ERROR");
        }
        // BR3.2-2: Trừ điểm, không để âm.
        acc.setTotalPoints(acc.getTotalPoints() - r.requiredPoints);
        r.quantity -= 1;
        Redemption.saveRedemption(userId, rewardId, r.requiredPoints);
        return true;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getRequiredPoints() { return requiredPoints; }
    public int getQuantity() { return quantity; }
    public String getDescription() { return description; }
}
