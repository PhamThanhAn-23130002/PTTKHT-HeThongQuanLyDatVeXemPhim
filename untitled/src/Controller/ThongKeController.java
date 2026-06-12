package Controller;

public class ThongKeController {
    public double calculateRevenue(String thoiGian) {
        if (thoiGian.equals("ALL")) return 15000000.0;
        return 2500000.0;
    }
    public int countTicketsSold(String thoiGian) {
        if (thoiGian.equals("ALL")) return 150;
        return 25;
    }
}
