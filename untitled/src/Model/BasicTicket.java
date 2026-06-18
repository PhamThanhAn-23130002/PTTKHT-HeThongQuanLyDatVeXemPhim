package Model;

public class BasicTicket extends Ticket{

    public BasicTicket(){
        type = "Vé Cơ Bản";
    }
    @Override
    public double cost() {
        return 60000;
    }
}
