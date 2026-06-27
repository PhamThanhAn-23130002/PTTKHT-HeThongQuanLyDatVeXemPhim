package Model;

import java.time.LocalDate;

public class Voucher {

    private String maVoucher;
    private double giaTriGiam;
    private double donHangToiThieu;
    private LocalDate ngayHetHan;
    private boolean trangThai;


    public Voucher() {
    }

    public Voucher(String maVoucher, double giaTriGiam, double donHangToiThieu, LocalDate ngayHetHan, boolean trangThai) {
        this.maVoucher = maVoucher;
        this.giaTriGiam = giaTriGiam;
        this.donHangToiThieu = donHangToiThieu;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public double getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(double giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public double getDonHangToiThieu() {
        return donHangToiThieu;
    }

    public void setDonHangToiThieu(double donHangToiThieu) {
        this.donHangToiThieu = donHangToiThieu;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}