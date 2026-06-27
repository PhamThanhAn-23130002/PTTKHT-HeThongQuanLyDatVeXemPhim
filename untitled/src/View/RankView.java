package View;

import Controller.RankController;
import Model.Account;
import Model.Rank;

import java.util.List;
import java.util.Scanner;

public class RankView {
    private Scanner scanner;
    private RankController rankController;

    public RankView(Scanner scanner, RankController rankController) {
        this.scanner = scanner;
        this.rankController = rankController;
    }

    // UC-3.3 (Sequence): View -> Controller.handleCheckRank(userId)
    public void handleCheckRank(Account currentUser) {
        hienThiBangHang();
        System.out.println("\nHạng hiện tại : " + currentUser.getRank());
        System.out.println("Tổng điểm     : " + (int) currentUser.getTotalPoints());
        System.out.print("Bạn có muốn kiểm tra thăng hạng? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Đã hủy thao tác.");
            return;
        }

        String newRank = rankController.handleCheckRank(currentUser.getId());
        if (newRank != null) {
            renderRankResult(newRank);
        }
    }

    private void hienThiBangHang() {
        List<Rank> ranks = Rank.getAllRanks();
        System.out.println("\n========== BẢNG HẠNG THÀNH VIÊN ==========");
        System.out.println(String.format("%-5s %-12s %-10s", "Mã", "Tên hạng", "Điểm tối thiểu"));
        for (Rank r : ranks) {
            System.out.println(String.format("%-5s %-12s %-10d",
                    r.getId(), r.getRankName(), r.getMinPoints()));
        }
    }

    public void renderRankResult(String newRank) {
        System.out.println("\n=== THĂNG HẠNG THÀNH CÔNG ===");
        System.out.println("Hạng mới của bạn: " + newRank);
        System.out.println("Chúc mừng bạn đã lên hạng!");
    }

    public void showMessage(String msg) { System.out.println("[THÔNG BÁO] " + msg); }
    public void showError(String msg)   { System.out.println("[LỖI] " + msg); }
}
