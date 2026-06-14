package Controller;

import Model.Account;
import Model.Role;
import Model.Session;

public class AccountController {
    private String currentSessionToken;

    // UC-1.1: Đăng nhập bằng email/password
    public Account login(String email, String password) {
        try {
            Account.validateCredentials(email, password);
            Account acc = Account.findByEmail(email);
            this.currentSessionToken = Session.createSession(acc.getId());
            return acc;
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return null;
        }
    }

    // UC-1.1 (luồng phụ): Đăng nhập bằng Google (mock)
    public Account loginWithGoogle(String googleEmail) {
        if (googleEmail == null || googleEmail.trim().isEmpty()) {
            System.out.println("Lỗi: Email Google không hợp lệ!");
            return null;
        }
        Account acc = Account.findByEmail(googleEmail);
        if (acc == null) {
            try {
                String defaultPwd = "Google@123";
                Account.registerUser(googleEmail, defaultPwd, defaultPwd);
                acc = Account.findByEmail(googleEmail);
                acc.setGoogleId("GG-" + acc.getId());
                System.out.println("(Lần đầu đăng nhập Google -> tạo tài khoản mới với MK mặc định: " + defaultPwd + ")");
            } catch (RuntimeException e) {
                handleError(e.getMessage());
                return null;
            }
        }
        this.currentSessionToken = Session.createSession(acc.getId());
        return acc;
    }

    // UC-1.5: Đăng ký tài khoản mới
    public boolean register(String email, String password, String confirmPassword) {
        try {
            return Account.registerUser(email, password, confirmPassword);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return false;
        }
    }

    private void handleError(String code) {
        switch (code) {
            case "INVALID_CREDENTIALS":
                System.out.println("Lỗi: Email hoặc mật khẩu không đúng!");
                break;
            case "ACCOUNT_LOCKED":
                System.out.println("Lỗi: Tài khoản đã bị khóa, vui lòng liên hệ quản trị viên!");
                break;
            case "EMAIL_ALREADY_EXISTS":
                System.out.println("Lỗi: Email đã được đăng ký!");
                break;
            case "PASSWORD_MISMATCH":
                System.out.println("Lỗi: Mật khẩu xác nhận không khớp!");
                break;
            case "INVALID_INPUT":
                System.out.println("Lỗi: Thông tin không hợp lệ (email sai định dạng hoặc mật khẩu < 8 ký tự / thiếu ký tự đặc biệt)!");
                break;
            default:
                System.out.println("Lỗi: " + code);
        }
    }

    public String getCurrentSessionToken() {
        return currentSessionToken;
    }

    public void setCurrentSessionToken(String token) {
        this.currentSessionToken = token;
    }
}
