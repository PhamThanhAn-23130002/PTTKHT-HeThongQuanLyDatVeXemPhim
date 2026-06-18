package Model;

public class Student_OldTicket extends Ticket{

    public Student_OldTicket(String id, String seatID, String roomID, String qrCode, boolean status){
        this.id = id;
        this.type = "Vé HSSV - Người cao tuổi";
        this.seatID = seatID;
        this.roomID = roomID;
        this.qrCode = qrCode;
        this.status = status;
    }

    @Override
    public double cost() {
        return 45000.0;
    }
}
