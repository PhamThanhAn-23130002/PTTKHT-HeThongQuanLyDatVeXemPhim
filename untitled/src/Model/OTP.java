package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OTP {
    private String email;
    private String otpCode;
    private LocalDateTime expiresAt;
    private boolean used;

    private static List<OTP> otps = new ArrayList<>();
    private static final long OTP_MINUTES = 10;

    public OTP(String email, String otpCode, LocalDateTime expiresAt, boolean used) {
        this.email = email;
        this.otpCode = otpCode;
        this.expiresAt = expiresAt;
        this.used = used;
    }

    // UC-1.4 (Sequence): Controller -> OTP.generateOTP(email)
    // Tạo mã OTP 6 số ngẫu nhiên, lưu kèm hạn sử dụng (10 phút).
    // Console app: in OTP ra màn hình thay vì gửi email thật.
    public static String generateOTP(String email) {
        for (OTP o : otps) {
            if (o.email.equalsIgnoreCase(email) && !o.used) {
                o.used = true;
            }
        }
        String code = String.format("%06d", new Random().nextInt(1_000_000));
        LocalDateTime expires = LocalDateTime.now().plusMinutes(OTP_MINUTES);
        otps.add(new OTP(email, code, expires, false));
        System.out.println("[MOCK EMAIL] OTP gửi đến " + email + ": " + code
                + " (hết hạn sau " + OTP_MINUTES + " phút)");
        return code;
    }

    // UC-1.4 (Sequence): Controller -> OTP.verifyOTP(email, otpCode)
    // Kiểm tra OTP đúng + chưa hết hạn + chưa dùng. Throw INVALID_OTP nếu sai.
    public static boolean verifyOTP(String email, String otpCode) {
        for (OTP o : otps) {
            if (o.email.equalsIgnoreCase(email) && o.otpCode.equals(otpCode)) {
                if (o.used || LocalDateTime.now().isAfter(o.expiresAt)) {
                    throw new RuntimeException("INVALID_OTP");
                }
                o.used = true;
                return true;
            }
        }
        throw new RuntimeException("INVALID_OTP");
    }

    public String getEmail() { return email; }
    public String getOtpCode() { return otpCode; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public boolean isUsed() { return used; }
}
