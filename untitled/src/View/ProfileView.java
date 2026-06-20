package View;

import Controller.ProfileController;
import Model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProfileView {
    private Scanner scanner;
    private ProfileController profileController;

    public ProfileView(Scanner scanner, ProfileController profileController) {
        this.scanner = scanner;
        this.profileController = profileController;
    }

    // UC-2.2 (Sequence): View -> Controller.viewProfile + updateProfile.
    public void displayProfileMenu(Account currentUser) {
        int choice = -1;
        while (choice != 0) {
            Account acc = profileController.viewProfile(currentUser.getId());
            if (acc == null) {
                System.out.println("Không tìm thấy tài khoản!");
                return;
            }
            hienThiHoSo(acc);

            System.out.println("\n1. Chỉnh sửa thông tin");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn: ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        xuLyCapNhatHoSo(acc);
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

    private void hienThiHoSo(Account acc) {
        System.out.println("\n------------------------------------------");
        System.out.println("            HỒ SƠ CÁ NHÂN");
        System.out.println("------------------------------------------");
        System.out.println("ID tài khoản : " + acc.getId());
        System.out.println("Tên hiển thị : " + acc.getUsername());
        System.out.println("Email        : " + acc.getEmail());
        System.out.println("Vai trò      : " + acc.getRole().getType());
        System.out.println("Trạng thái   : " + acc.getStatus());
        System.out.println("Hạng         : " + acc.getRank());
        System.out.println("Tổng điểm    : " + (int) acc.getTotalPoints());
        System.out.println("Google ID    : " + (acc.getGoogleId() == null ? "(chưa liên kết)" : acc.getGoogleId()));
    }

    // UC-2.2: thu thập dữ liệu mới và gọi Controller.updateProfile.
    private void xuLyCapNhatHoSo(Account acc) {
        System.out.println("\n--- CHỈNH SỬA HỒ SƠ ---");
        System.out.println("(Để trống và Enter để giữ nguyên giá trị cũ)");
        System.out.print("Tên hiển thị mới [" + acc.getUsername() + "]: ");
        String newUsername = scanner.nextLine();
        System.out.print("Email mới [" + acc.getEmail() + "]: ");
        String newEmail = scanner.nextLine();

        Map<String, String> data = new HashMap<>();
        if (newUsername != null && !newUsername.trim().isEmpty()) {
            data.put("username", newUsername);
        }
        if (newEmail != null && !newEmail.trim().isEmpty()) {
            data.put("email", newEmail);
        }

        if (data.isEmpty()) {
            System.out.println("Không có thay đổi.");
            return;
        }

        System.out.print("Xác nhận lưu thay đổi? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Đã hủy chỉnh sửa.");
            return;
        }

        boolean ok = profileController.updateProfile(acc.getId(), data);
        if (ok) {
            System.out.println("Cập nhật hồ sơ thành công!");
        }
    }
}
