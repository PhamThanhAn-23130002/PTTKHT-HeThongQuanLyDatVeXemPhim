package Controller;

import Model.Account;
import Model.OTP;
import Model.Role;
import Model.Session;

public class AccountController {
    private String currentSessionToken;

    // UC-1.1 (Sequence): View -> Controller.login(email, password)
    // Account.validateCredentials -> Session.createSession.
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

    // UC-1.1 (luồng phụ - Sequence): Google flow.
    // findByEmail; nếu chưa có -> registerUser tạo mới; sau đó createSession.
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
                System.out.println("(Lần đầu đăng nhập Google -> tạo tài khoản mới, MK mặc định: " + defaultPwd + ")");
            } catch (RuntimeException e) {
                handleError(e.getMessage());
                return null;
            }
        }
        this.currentSessionToken = Session.createSession(acc.getId());
        return acc;
    }

    // UC-1.5 (Sequence): Controller -> Account.registerUser(email, pw, confirmPw).
    public boolean register(String email, String password, String confirmPassword) {
        try {
            return Account.registerUser(email, password, confirmPassword);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return false;
        }
    }

    // UC-1.3 (Sequence): Controller -> Session.deleteSession(sessionId).
    // Throw SESSION_DELETE_FAILED nếu xóa không thành công.
    public boolean logout(String sessionToken) {
        try {
            boolean ok = Session.deleteSession(sessionToken);
            if (!ok) {
                throw new RuntimeException("SESSION_DELETE_FAILED");
            }
            this.currentSessionToken = null;
            return true;
        } catch (RuntimeException e) {
            if ("SESSION_DELETE_FAILED".equals(e.getMessage())) {
                System.out.println("Lỗi: Không thể đăng xuất, vui lòng thử lại!");
            } else {
                System.out.println("Lỗi: " + e.getMessage());
            }
            return false;
        }
    }

    // UC-1.4 (Sequence - bước 1): Controller -> Account.findByEmail -> OTP.generateOTP.
    // Throw EMAIL_NOT_FOUND nếu email không tồn tại.
    public boolean requestPasswordReset(String email) {
        try {
            Account acc = Account.findByEmail(email);
            if (acc == null) {
                throw new RuntimeException("EMAIL_NOT_FOUND");
            }
            OTP.generateOTP(email);
            return true;
        } catch (RuntimeException e) {
            if ("EMAIL_NOT_FOUND".equals(e.getMessage())) {
                System.out.println("Lỗi: Email không tồn tại trong hệ thống!");
            } else {
                System.out.println("Lỗi: " + e.getMessage());
            }
            return false;
        }
    }

    // UC-1.4 (Sequence - bước 2): Controller -> OTP.verifyOTP(email, otpCode).
    public boolean verifyResetOTP(String email, String otpCode) {
        try {
            return OTP.verifyOTP(email, otpCode);
        } catch (RuntimeException e) {
            if ("INVALID_OTP".equals(e.getMessage())) {
                System.out.println("Lỗi: Mã OTP không đúng hoặc đã hết hạn!");
            } else {
                System.out.println("Lỗi: " + e.getMessage());
            }
            return false;
        }
    }

    // UC-1.4 (Sequence - bước 3): Controller -> Account.updatePassword(email, pw, confirmPw).
    public boolean resetPassword(String email, String password, String confirmPassword) {
        try {
            return Account.updatePassword(email, password, confirmPassword);
        } catch (RuntimeException e) {
            handleError(e.getMessage());
            return false;
        }
    }

    // UC-1.4 (Alternative 6.1): Gửi lại OTP -> OTP.generateOTP() lại.
    public boolean resendOTP(String email) {
        OTP.generateOTP(email);
        return true;
    }

    // UC-1.2 (Sequence): Controller -> Session.validateSession -> Account.findById.
    // Throw SESSION_INVALID nếu phiên không hợp lệ -> View redirect login.
    public Account manageAccount(String sessionToken, String userId) {
        try {
            if (sessionToken == null || !Session.validateSession(sessionToken)) {
                throw new RuntimeException("SESSION_INVALID");
            }
            Account acc = Account.findById(userId);
            if (acc == null) {
                throw new RuntimeException("SESSION_INVALID");
            }
            return acc;
        } catch (RuntimeException e) {
            if ("SESSION_INVALID".equals(e.getMessage())) {
                System.out.println("Lỗi: Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại!");
            } else {
                System.out.println("Lỗi: " + e.getMessage());
            }
            return null;
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
            case "INVALID_PASSWORD_FORMAT":
                System.out.println("Lỗi: Mật khẩu sai định dạng (cần >=8 ký tự và có ký tự đặc biệt)!");
                break;
            case "INVALID_INPUT":
                System.out.println("Lỗi: Thông tin không hợp lệ (email sai định dạng hoặc mật khẩu <8 ký tự / thiếu ký tự đặc biệt)!");
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
