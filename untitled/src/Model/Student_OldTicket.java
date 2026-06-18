package Model;

public class Student_OldTicket extends Ticket{

    public Student_OldTicket(){
        type = "Vé HSSV - Người cao tuổi";
    }

    @Override
    public double cost() {
        return 0;
    }
}
