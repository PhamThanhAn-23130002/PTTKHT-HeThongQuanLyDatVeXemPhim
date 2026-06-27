package View;

import Controller.RewardController;
import Model.Account;
import Model.Reward;

import java.util.List;
import java.util.Scanner;

public class RewardView {
    private Scanner scanner;
    private RewardController rewardController;

    public RewardView(Scanner scanner, RewardController rewardController) {
        this.scanner = scanner;
        this.rewardController = rewardController;
    }

    // UC-3.2 (Sequence): View -> Controller.getAvailableRewards -> Controller.checkPoints
    //                  -> Controller.redeemReward.
    public void handleViewRewards(Account currentUser) {
        List<Reward> rewards = rewardController.getAvailableRewards();
        if (rewards.isEmpty()) {
            showMessage("Không có quà khả dụng");
            return;
        }
        System.out.println("\n--- Tổng điểm của bạn: "
                + (int) currentUser.getTotalPoints() + " ---");
        renderRewardList(rewards);

        System.out.print("Nhập mã quà muốn đổi (vd RW1, hoặc 0 để hủy): ");
        String rewardId = scanner.nextLine().trim();
        if (rewardId.equals("0") || rewardId.isEmpty()) {
            System.out.println("Đã hủy đổi quà.");
            return;
        }

        Reward reward = rewardController.checkPoints(currentUser.getId(), rewardId);
        if (reward == null) return;

        renderRewardConfirm(reward);
        System.out.print("Xác nhận đổi quà? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Đã hủy đổi quà, quay lại danh sách.");
            handleViewRewards(currentUser);
            return;
        }

        boolean ok = rewardController.redeemReward(currentUser.getId(), rewardId);
        if (ok) {
            showSuccess("Đổi quà thành công! Điểm còn lại: "
                    + (int) currentUser.getTotalPoints());
        }
    }

    public void renderRewardList(List<Reward> rewards) {
        System.out.println("\n========== DANH SÁCH QUÀ ĐỔI ==========");
        System.out.println(String.format("%-5s %-25s %-10s %-10s %-30s",
                "Mã", "Tên quà", "Điểm cần", "Còn lại", "Mô tả"));
        for (Reward r : rewards) {
            System.out.println(String.format("%-5s %-25s %-10d %-10d %-30s",
                    r.getId(), r.getName(), r.getRequiredPoints(),
                    r.getQuantity(), r.getDescription()));
        }
    }

    public void renderRewardConfirm(Reward reward) {
        System.out.println("\n--- XÁC NHẬN ĐỔI QUÀ ---");
        System.out.println("Quà         : " + reward.getName());
        System.out.println("Mô tả       : " + reward.getDescription());
        System.out.println("Điểm cần trừ: " + reward.getRequiredPoints());
    }

    public void showSuccess(String msg) { System.out.println("[THÀNH CÔNG] " + msg); }
    public void showError(String msg)   { System.out.println("[LỖI] " + msg); }
    public void showMessage(String msg) { System.out.println("[THÔNG BÁO] " + msg); }
}
