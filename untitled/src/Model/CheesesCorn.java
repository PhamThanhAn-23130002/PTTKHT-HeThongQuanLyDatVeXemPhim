package Model;

public class CheesesCorn extends TicketDecorator{
    private int quantity;

    public CheesesCorn(Ticket ticket, int quantity) {
        super(ticket);
        this.quantity = quantity;
        this.ticket.type = "Bắp Phô mai";
    }

    @Override
    public double cost() {
        double giaBap = 75000.0 * quantity;
        return ticket.cost() + giaBap;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String information() {
        return super.information() + String.format(this.type+"(x%d)", quantity);
    }
}
