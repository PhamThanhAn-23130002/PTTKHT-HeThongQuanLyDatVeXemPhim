package Model;
import java.util.List;

public class Order {
    private String id;
    private Ticket ticket;
    private int numSeats;
    private List<Payment> paymentList;
    private String status;

    public Order(String id, Ticket ticket, int numSeats) {
        this.id = id;
        this.ticket = ticket;
        this.numSeats = numSeats;
        this.status = "Pending";
    }

    public double getBaseCost() { return ticket.cost(); }
    public int getNumSeats() { return numSeats; }
    public void updateOrderStatus(String status) { this.status = status; }
    public String getId() { return id; }
    public String getStatus() { return status; }
}