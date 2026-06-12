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
        System.out.println("\n--- YÊU CẦU ĐĂNG NHẬP ---");
        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();
        Account result = accountController.login(username, password);
        if (result != null) {
            this.currentUser = result; //
            System.out.println("Đăng nhập thành công!");
        } else {
            System.out.println("Sai tên đăng nhập hoặc mật khẩu. Vui lòng thử lại!");
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
