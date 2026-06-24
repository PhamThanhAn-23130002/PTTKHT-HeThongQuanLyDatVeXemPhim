package Model;

public class Seat {
    private String id;
    private String type; // Thường, VIP
    private String status; // Trống, Đang giữ, Đã bán

    public Seat(String id, String type, String status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public boolean isAvailable() {
        return this.status.equals("Trống");
    }

    public boolean markAsHold() {
        if (isAvailable()) {
            this.status = "Đang giữ";
            return true;
        }
        return false;
    }

    public boolean markAsSold() {
        this.status = "Đã bán";
        return true;
    }

    public boolean markAsEmpty() {
        this.status = "Trống";
        return true;
    }

    public String getId() { return id; }
    public String getStatus() { return status; }
}