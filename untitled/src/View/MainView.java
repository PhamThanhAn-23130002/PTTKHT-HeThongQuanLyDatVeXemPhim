package View;

import Controller.AccountController;
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


    public MainView() {
        this.scanner = new Scanner(System.in);
        this.accountView = new AccountView(scanner);
        this.cinemaManagerView = new CinemaManagerView(scanner);
        this.bookingView = new BookingView(scanner);
        this.operationView = new OperationView(scanner);
        this.accountController = new AccountController();
    }

    public void start() {
        System.out.println("==========================================");
        System.out.println("   CHÀO MỪNG ĐẾN VỚI RẠP CHIẾU PHIM CỦA NHÓM 6");
        System.out.println("==========================================");

        while (this.currentUser == null) {
            hienThiManHinhDangNhap();
        }
        displayMainMenu();
    }

    private void hienThiManHinhDangNhap() {
        System.out.println("\n------------------------------------------");
        System.out.println("            MENU TÀI KHOẢN");
        System.out.println("------------------------------------------");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký tài khoản mới");
        System.out.println("3. Đăng nhập bằng Google");
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
            case "0":
                System.out.println("Bái bai nhóooo! Đang đóng hệ thống...");
                System.exit(0);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
        }
    }

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

            System.out.println("0. Đăng xuất & Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("-> Chuyển đến View Quản lý Tài khoản...");
                            accountView.displayAccountMenu();
                        break;
                    case 2:
                        System.out.println("-> Chuyển đến View Quản lý Phim/Phòng...");
                            cinemaManagerView.displayManagerMenu();
                        break;
                    case 3:
                        System.out.println("-> Chuyển đến View Đặt vé...");
                            bookingView.displayBookingMenu();
                        break;
                    case 4:
                        operationView.displayOperationMenu();
                        break;
                    case 0:
                        System.out.println("Bái bai nhóooo! Đang đóng hệ thống...");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên!");
            }
        }
    }
}
