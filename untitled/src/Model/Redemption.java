package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Redemption {
    private String id;
    private LocalDateTime date;
    private int pointsUsed;
    private String rewardId;
    private String userId;

    private static List<Redemption> redemptions = new ArrayList<>();

    static {
        redemptions.add(new Redemption("RD001", LocalDateTime.of(2026, 5, 10, 14, 0),
                30, "RW2", "U002"));
    }

    public Redemption(String id, LocalDateTime date, int pointsUsed, String rewardId, String userId) {
        this.id = id;
        this.date = date;
        this.pointsUsed = pointsUsed;
        this.rewardId = rewardId;
        this.userId = userId;
    }

    // UC-3.2 (Sequence): Controller -> RedemptionModel.saveRedemption(...)
    public static boolean saveRedemption(String userId, String rewardId, int points) {
        String newId = String.format("RD%03d", redemptions.size() + 1);
        redemptions.add(new Redemption(newId, LocalDateTime.now(), points, rewardId, userId));
        return true;
    }

    public static List<Redemption> findByUserId(String userId) {
        List<Redemption> result = new ArrayList<>();
        if (userId == null) return result;
        for (Redemption rd : redemptions) {
            if (userId.equals(rd.userId)) result.add(rd);
        }
        return result;
    }

    public String getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public int getPointsUsed() { return pointsUsed; }
    public String getRewardId() { return rewardId; }
    public String getUserId() { return userId; }
}
