package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Rank {
    private String id;
    private String rankName;
    private int minPoints;

    private static List<Rank> ranks = new ArrayList<>();

    static {
        ranks.add(new Rank("R1", "Bronze", 0));
        ranks.add(new Rank("R2", "Silver", 100));
        ranks.add(new Rank("R3", "Gold", 300));
        ranks.add(new Rank("R4", "Platinum", 500));
        ranks.add(new Rank("R5", "Diamond", 1000));
    }

    public Rank(String id, String rankName, int minPoints) {
        this.id = id;
        this.rankName = rankName;
        this.minPoints = minPoints;
    }

    // UC-3.3 (Sequence): Controller -> RankModel.getAllRanks()
    public static List<Rank> getAllRanks() {
        List<Rank> sorted = new ArrayList<>(ranks);
        sorted.sort(Comparator.comparingInt(r -> r.minPoints));
        return sorted;
    }

    // UC-3.3 (Sequence): Controller -> RankModel.checkRankEligibility(userId)
    // Trả về tên hạng cao nhất user đủ điểm. Throw NOT_ELIGIBLE nếu không thể nâng.
    public static String checkRankEligibility(String userId) {
        Account acc = Account.findById(userId);
        if (acc == null) {
            throw new RuntimeException("NOT_ELIGIBLE");
        }
        int totalPoints = (int) acc.getTotalPoints();
        String currentRank = acc.getRank();

        List<Rank> sorted = new ArrayList<>(ranks);
        sorted.sort((a, b) -> b.minPoints - a.minPoints);

        String eligibleRank = null;
        for (Rank r : sorted) {
            if (totalPoints >= r.minPoints) {
                eligibleRank = r.rankName;
                break;
            }
        }

        if (eligibleRank == null) {
            throw new RuntimeException("NOT_ELIGIBLE");
        }
        // BR3.3-2: chỉ nâng lên hạng cao hơn.
        int currentIndex = indexOf(currentRank);
        int eligibleIndex = indexOf(eligibleRank);
        if (eligibleIndex <= currentIndex) {
            throw new RuntimeException("NOT_ELIGIBLE");
        }
        return eligibleRank;
    }

    // UC-3.3 (Sequence): Controller -> RankModel.upgradeRank(userId, newRank)
    public static boolean upgradeRank(String userId, String newRank) {
        Account acc = Account.findById(userId);
        if (acc == null || newRank == null) {
            throw new RuntimeException("RANK_UPDATE_ERROR");
        }
        acc.setRank(newRank);
        return true;
    }

    private static int indexOf(String rankName) {
        List<Rank> sorted = getAllRanks();
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).rankName.equalsIgnoreCase(rankName)) return i;
        }
        return -1;
    }

    public String getId() { return id; }
    public String getRankName() { return rankName; }
    public int getMinPoints() { return minPoints; }
}
