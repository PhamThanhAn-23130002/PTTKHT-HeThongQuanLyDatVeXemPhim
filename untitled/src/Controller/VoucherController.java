package Controller;

import Model.Voucher;
import java.util.ArrayList;
import java.util.List;

public class VoucherController {
    private List<Voucher> voucherList = new ArrayList<>();

    public void addVoucher(Voucher v) {
        voucherList.add(v);
    }

    public Voucher findVoucherByCode(String code) {
        for (Voucher v : voucherList) {
            if (v.getCode().equals(code)) return v;
        }
        return null;
    }

    public boolean updateVoucher(String code, double newDiscountValue, int newQuantity) {
        Voucher v = findVoucherByCode(code);
        if (v != null) {
            v.setDiscountValue(newDiscountValue);
            v.setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    public boolean deleteVoucher(String code) {
        Voucher v = findVoucherByCode(code);
        if (v != null) {
            voucherList.remove(v);
            return true;
        }
        return false;
    }

    public List<Voucher> getAllVouchers() {
        return voucherList;
    }
}