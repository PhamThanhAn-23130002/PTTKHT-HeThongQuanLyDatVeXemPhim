package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;

public class SoatVeController {
    List<Ticket> dbVe = new ArrayList<Ticket>();
    public List<Ticket> getDbVe(){
        Ticket ticket1 = new BasicTicket("001", "H1", "5", "2351896325", true);
        ticket1 = new CheesesCorn(ticket1, 1);
        Ticket ticket2 = new BasicTicket("002", "G5", "3", "2365142589", true);
        ticket2 = new CaramelCorn(ticket2, 1);
        Ticket ticket3 = new BasicTicket("003", "J4", "1", "3698521472", true);
        ticket3 = new SoftDrink(ticket3, 2);

        dbVe.add(ticket1); dbVe.add(ticket2); dbVe.add(ticket3);
        return dbVe;

    }
    public boolean verifyTicketCode(String qrcodeData) {
        for (Ticket ticket : getDbVe()) {
            if (ticket.getQrCode().equals(qrcodeData)) {
                Ticket ticketObject = ticket.getTicketData(qrcodeData);
                if (ticketObject != null) {
                    boolean isUnused = true;
                    if (isUnused) {
                        ticket.updateStatus("Đã sử dụng");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean verifyQrCode(String qrcodeData) {
        return verifyTicketCode(qrcodeData);
    }
}
