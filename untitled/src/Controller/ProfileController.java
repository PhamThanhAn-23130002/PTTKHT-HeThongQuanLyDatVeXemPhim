package Controller;

import Model.Account;
import java.util.Map;

public class ProfileController {

    // UC-2.2 (Sequence): Controller -> Account.findById(userId) -> hiển thị thông tin.
    public Account viewProfile(String userId) {
        return Account.findById(userId);
    }

    // UC-2.2 (Sequence): Controller -> Account.updateProfile(userId, data).
    // Throw INVALID_PROFILE_DATA / SYSTEM_ERROR -> View hiển thị lỗi tương ứng.
    public boolean updateProfile(String userId, Map<String, String> profileData) {
        try {
            return Account.updateProfile(userId, profileData);
        } catch (RuntimeException e) {
            String code = e.getMessage();
            if ("INVALID_PROFILE_DATA".equals(code)) {
                System.out.println("Lỗi: Dữ liệu hồ sơ không hợp lệ (email sai định dạng / tên <2 ký tự / email đã tồn tại)!");
            } else if ("SYSTEM_ERROR".equals(code)) {
                System.out.println("Lỗi hệ thống, vui lòng thử lại sau!");
            } else {
                System.out.println("Lỗi: " + code);
            }
            return false;
        }
    }
}
