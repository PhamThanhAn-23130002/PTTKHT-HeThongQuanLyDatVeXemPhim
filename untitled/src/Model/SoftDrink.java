package Model;

public class SoftDrink extends TicketDecorator{
    private int quantity;

    public SoftDrink(Ticket ticket, int quantity) {
        super(ticket);
        this.quantity = quantity;
        this.ticket.type = "Nước ngọt có gas";
    }

    @Override
    public double cost() {
        double giaBap = 45000.0 * quantity;
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
