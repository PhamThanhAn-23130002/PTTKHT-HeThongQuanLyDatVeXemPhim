package Model;

public class BasicTicket extends Ticket{

    public BasicTicket(String id, String seatID, String roomID, String qrCode, boolean status){
        this.id = id;
        this.type = "Vé Cơ Bản";
        this.seatID = seatID;
        this.roomID = roomID;
        this.qrCode = qrCode;
        this.status = status;
    }

    @Override
    public double cost() {
        return 60000.0;
    }

}
