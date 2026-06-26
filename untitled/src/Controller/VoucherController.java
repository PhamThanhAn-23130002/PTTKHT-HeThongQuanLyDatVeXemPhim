package Controller;

import Model.Voucher;

public class VoucherController {

    public boolean kiemTraVoucher(Voucher voucher, double tongDonHang) {
        if (voucher == null) return false;

        if (tongDonHang >= voucher.getDonHangToiThieu() && voucher.isTrangThai()) {
            return true;
        }
        return false;
    }

    public double tinhSoTienGiam(Voucher voucher, double tongDonHang) {
        if (kiemTraVoucher(voucher, tongDonHang)) {
            return voucher.getGiaTriGiam();
        }
        return 0;
    }
}