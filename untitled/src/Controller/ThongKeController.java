package Controller;

import Model.RevenueReport;
import Model.Ticket;

import java.util.List;

public class ThongKeController {
    public Object getStatisticData(String filterCriteria) {
        RevenueReport reportEntity = new RevenueReport();
        List<Ticket> rawData = reportEntity.findTicketsByFilter(filterCriteria);
        if (rawData == null || rawData.isEmpty()) {
            return null;
        }
        Object chartData = calculateRevenue(rawData);
        return chartData;
    }

    private Object calculateRevenue(List<Ticket> rawData) {
        return "Tổng doanh thu: 15.000.000 VNĐ";
    }

    public String exportReport(Object chartData, String formatType) {
        RevenueReport reportEntity = new RevenueReport();
        reportEntity.generatefile();
        return "C:/Downloads/BaoCaoDoanhThu." + formatType.toLowerCase();
    }
}
