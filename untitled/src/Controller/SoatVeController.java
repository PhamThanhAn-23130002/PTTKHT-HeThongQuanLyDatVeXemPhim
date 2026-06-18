package Controller;

import Model.BasicTicket;
import Model.Ticket;

public class SoatVeController {
    public boolean verifyTicketCode(String qrcodeData) {
        Ticket ticketEntity = new BasicTicket();
        Object ticketObject = ticketEntity.getTicketData(qrcodeData);

        if (ticketObject != null) {
            boolean isUnused = true;
            if (isUnused) {
                ticketEntity.updateStatus("Đã sử dụng");
                return true;
            }
        }
        return false;
    }

    public boolean verifyQrCode(String qrcodeData) {
        return verifyTicketCode(qrcodeData);
    }
}
