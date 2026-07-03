package View;

import Controller.AccountController;
import Controller.ProfileController;
import Model.Account;
import java.util.Scanner;

public class MainView {
    private Scanner scanner;
    private OperationView operationView;
    private BookingView bookingView;
    private AccountView accountView;
    private CinemaManagerView cinemaManagerView;
    private Account currentUser;
    private AccountController accountController;
    private ProfileController profileController;


    public MainView() {
        this.scanner = new Scanner(System.in);
        this.accountController = new AccountController();
        this.profileController = new ProfileController();
        this.accountView = new AccountView(scanner, accountController, profileController);
        this.cinemaManagerView = new CinemaManagerView(scanner);
        this.bookingView = new BookingView(scanner);
        this.operationView = new OperationView(scanner);
    }

    public void start() {
        System.out.println("==========================================");
        System.out.println("   CHÀO MỪNG ĐẾN VỚI RẠP CHIẾU PHIM CỦA NHÓM 6");
        System.out.println("==========================================");

        while (true) {
            while (this.currentUser == null) {
                hienThiManHinhDangNhap();
            }
            displayMainMenu();
        }
    }

    private void hienThiManHinhDangNhap() {
        System.out.println("\n------------------------------------------");
        System.out.println("            MENU TÀI KHOẢN");
        System.out.println("------------------------------------------");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký tài khoản mới");
        System.out.println("3. Đăng nhập bằng Google");
        System.out.println("4. Quên mật khẩu");
        System.out.println("0. Thoát chương trình");
        System.out.print("Nhập lựa chọn: ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                xuLyDangNhap();
                break;
            case "2":
                xuLyDangKy();
                break;
            case "3":
                xuLyDangNhapGoogle();
                break;
            case "4":
                xuLyQuenMatKhau();
                break;
            case "0":
                System.out.println(" Đang đóng hệ thống, xin hẹn gặp lại!");
                System.exit(0);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
        }
    }

    // UC-1.1: Đăng nhập bằng email/password
    private void xuLyDangNhap() {
        System.out.println("\n--- ĐĂNG NHẬP ---");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();

        Account result = accountController.login(email, password);
        if (result != null) {
            this.currentUser = result;
            System.out.println("Đăng nhập thành công! Xin chào " + result.getUsername()
                    + " (" + result.getRole().getType() + ")");
            System.out.println("Session Token: " + accountController.getCurrentSessionToken());
        }
    }

    // UC-1.5: Đăng ký tài khoản mới
    private void xuLyDangKy() {
        System.out.println("\n--- ĐĂNG KÝ TÀI KHOẢN MỚI ---");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Mật khẩu (>=8 ký tự, có ký tự đặc biệt): ");
        String password = scanner.nextLine();
        System.out.print("Xác nhận mật khẩu: ");
        String confirmPassword = scanner.nextLine();

        boolean ok = accountController.register(email, password, confirmPassword);
        if (ok) {
            System.out.println("Đăng ký thành công! Vui lòng đăng nhập để sử dụng hệ thống.");
        }
    }

    // UC-1.1 (luồng phụ): Đăng nhập bằng Google (mock)
    private void xuLyDangNhapGoogle() {
        System.out.println("\n--- ĐĂNG NHẬP BẰNG GOOGLE (MOCK) ---");
        System.out.print("Nhập email Google: ");
        String googleEmail = scanner.nextLine().trim();

        Account result = accountController.loginWithGoogle(googleEmail);
        if (result != null) {
            this.currentUser = result;
            System.out.println("Đăng nhập Google thành công! Xin chào " + result.getUsername()
                    + " (" + result.getRole().getType() + ")");
            System.out.println("Session Token: " + accountController.getCurrentSessionToken());
        }
    }

    // UC-1.4: Quên mật khẩu (findByEmail -> generateOTP -> verifyOTP -> updatePassword)
    private void xuLyQuenMatKhau() {
        System.out.println("\n--- QUÊN MẬT KHẨU ---");
        System.out.print("Email tài khoản: ");
        String email = scanner.nextLine().trim();

        if (!accountController.requestPasswordReset(email)) {
            return;
        }

        boolean otpOk = false;
        while (!otpOk) {
            System.out.print("Nhập mã OTP (hoặc 'r' để gửi lại, 'c' để hủy): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("c")) {
                System.out.println("Đã hủy đặt lại mật khẩu.");
                return;
            }
            if (input.equalsIgnoreCase("r")) {
                accountController.resendOTP(email);
                continue;
            }
            otpOk = accountController.verifyResetOTP(email, input);
        }

        System.out.print("Mật khẩu mới (>=8 ký tự, có ký tự đặc biệt): ");
        String pw = scanner.nextLine();
        System.out.print("Xác nhận mật khẩu mới: ");
        String confirmPw = scanner.nextLine();

        if (accountController.resetPassword(email, pw, confirmPw)) {
            System.out.println("Đặt lại mật khẩu thành công! Vui lòng đăng nhập.");
        }
    }

    public void displayMainMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("==========================================");
            System.out.println("HỆ THỐNG ĐẶT VÉ XEM PHIM - MENU CHÍNH");
            System.out.println("==========================================");
            // 1. Phân quyền: chỉ có quản lý mới dô được
            if (currentUser.getRole().getType().equals("Manager")) {
                System.out.println("1. Quản lý Phim, Phòng & Lịch chiếu");
                System.out.println("2. Quản lý Tài khoản & Phân quyền");
            }

            // 2. Phân quyền: dành cho khách hàng đặt vé
            if (currentUser.getRole().getType().equals("Customer")) {
                System.out.println("3. Đặt vé & Thanh toán");
                System.out.println("4. Xem hồ sơ & Lịch sử cá nhân");
            }

            // 3. Phân quyền: nhân viên hoặc quản lý thì được soát vé
            if (currentUser.getRole().getType().equals("Staff") || currentUser.getRole().getType().equals("Manager")) {
                System.out.println("5. Quản lý Vận hành (Soát vé, Thống kê, Giá)");
            }

            System.out.println("0. Đăng xuất");
            System.out.print("Nhập lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("-> Chuyển đến View Quản lý Phim/Phòng...");
                        cinemaManagerView.displayManagerMenu();
                        break;
                    case 2:
                        System.out.println("-> Chuyển đến View Quản lý Tài khoản....");
                        accountView.displayAccountMenu(currentUser);
                        break;
                    case 3:
                        System.out.println("-> Chuyển đến View Đặt vé...");
                            bookingView.displayBookingMenu();
                        break;
                    case 4:
                        // Customer xem hồ sơ + lịch sử = vào AccountView (UC-1.2)
                        accountView.displayAccountMenu(currentUser);
                        break;
                    case 5:
                        operationView.displayOperationMenu();
                        break;
                    case 0:
                        if (xuLyDangXuat()) {
                            return;
                        } else {
                            choice = -1;
                        }
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên!");
            }
        }
    }

    // UC-1.3: Đăng xuất - xác nhận -> Session.deleteSession -> redirect login.
    // Trả về true nếu đã đăng xuất thành công (caller cần thoát menu).
    private boolean xuLyDangXuat() {
        System.out.print("Bạn có chắc muốn đăng xuất? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Đã hủy đăng xuất.");
            return false;
        }
        boolean ok = accountController.logout(accountController.getCurrentSessionToken());
        if (ok) {
            System.out.println("Đăng xuất thành công! Hẹn gặp lại " + currentUser.getUsername() + ".");
            this.currentUser = null;
            return true;
        }
        return false;
    }
}
