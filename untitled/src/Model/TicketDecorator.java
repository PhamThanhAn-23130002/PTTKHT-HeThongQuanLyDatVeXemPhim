package Model;

public abstract class TicketDecorator extends  Ticket{
    protected Ticket ticket;

    public  TicketDecorator(Ticket ticket){
        this.ticket = ticket;
    }

    @Override
    public String information() {
        return ticket.information();
    }


}
