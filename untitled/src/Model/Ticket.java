package Model;

import java.time.LocalDateTime;

public abstract class Ticket {
    protected String id, type, seatID, roomID, qrCode;
    protected boolean status;
    protected String scannedAt;

    public String information() {
        return String.format("Vé phim [Mã: %s | Hạng: %s | Ghế: %s | Phòng: %s]",
                id, type, seatID, roomID);
    }
    public abstract double cost();

    public Ticket getTicketData(String qrCodeInput) {
        if (this.qrCode != null && this.qrCode.equals(qrCodeInput)) {
            return this;
        }
        return null;
    }

    public void updateStatus(String statusStr) {
        if (statusStr.equalsIgnoreCase("Đã sử dụng")) {
            this.status = false;
            this.scannedAt = LocalDateTime.now().toString();
        } else {
            this.status = true;
        }
    }
}
