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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
