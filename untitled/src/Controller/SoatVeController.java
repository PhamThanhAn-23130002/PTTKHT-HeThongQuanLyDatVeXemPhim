package Controller;

public class SoatVeController {
    public boolean verifyTicketCode(String maVe) {
        if (maVe != null && maVe.startsWith("QR")) {
            return true;
        }
        return false;
    }
}
