package View;

import Controller.AccountController;
import Controller.ProfileController;
import Model.Account;

import java.util.Scanner;

public class AccountView {
    private Scanner scanner;
    private AccountController accountController;
    private ProfileController profileController;
    private ProfileView profileView;

    public AccountView(Scanner scanner) {
        this.scanner = scanner;
    }

    public AccountView(Scanner scanner, AccountController accountController, ProfileController profileController) {
        this.scanner = scanner;
        this.accountController = accountController;
        this.profileController = profileController;
        this.profileView = new ProfileView(scanner, profileController);
    }

    // UC-1.2 (Sequence): View -> Controller.manageAccount(sessionId, userId)
    // -> Session.validateSession + Account.findById -> View hiển thị menu 5 chức năng.
    public void displayAccountMenu(Account currentUser) {
        Account verified = accountController.manageAccount(
                accountController.getCurrentSessionToken(),
                currentUser.getId());
        if (verified == null) {
            return;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n------------------------------------------");
            System.out.println("QUẢN LÝ TÀI KHOẢN & KHÁCH HÀNG");
            System.out.println("Xin chào: " + verified.getUsername()
                    + " | Hạng: " + verified.getRank()
                    + " | Điểm: " + (int) verified.getTotalPoints());
            System.out.println("------------------------------------------");
            System.out.println("1. Quản lý hồ sơ (UC-2.2)");
            System.out.println("2. Lịch sử đặt vé (UC-2.1)");
            System.out.println("3. Tích điểm (UC-3.1)");
            System.out.println("4. Đổi quà (UC-3.2)");
            System.out.println("5. Thăng hạng (UC-3.3)");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        profileView.displayProfileMenu(verified);
                        break;
                    case 2:
                        System.out.println("-> UC-2.1 (Lịch sử đặt vé) đang phát triển...");
                        break;
                    case 3:
                        System.out.println("-> UC-3.1 (Tích điểm) đang phát triển...");
                        break;
                    case 4:
                        System.out.println("-> UC-3.2 (Đổi quà) đang phát triển...");
                        break;
                    case 5:
                        System.out.println("-> UC-3.3 (Thăng hạng) đang phát triển...");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }

    // Overload cũ giữ lại để tránh phá API cũ; không nên dùng vì thiếu currentUser.
    public void displayAccountMenu() {
        System.out.println("Lỗi: Vui lòng đăng nhập trước khi vào quản lý tài khoản.");
    }
}
