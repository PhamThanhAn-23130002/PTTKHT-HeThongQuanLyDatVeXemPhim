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

    @Override
    public String getQrCode() {
        return ticket.getQrCode();
    }

    @Override
    public Ticket getTicketData(String qrCodeInput) {
        return ticket.getTicketData(qrCodeInput);
    }

    @Override
    public void updateStatus(String statusStr) {
        ticket.updateStatus(statusStr);
    }
}
