package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RevenueReport {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private List<Ticket> totalRevenue;
    private String typeReport;

    public RevenueReport() {
        this.totalRevenue = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public List<Ticket> findTicketsByFilter(String criteria) {
        List<Ticket> resultList = new ArrayList<>();
        if (criteria != null && !criteria.isEmpty()) {
            Ticket ticket1 = new BasicTicket("001", "H1", "5", "2351896325", true);
            ticket1 = new CheesesCorn(ticket1, 1);
            Ticket ticket2 = new Student_OldTicket("002", "G5", "3", "2365142589", true);
            ticket2 = new CaramelCorn(ticket2, 1);
            Ticket ticket3 = new BasicTicket("003", "J4", "1", "3698521472", true);
            ticket3 = new SoftDrink(ticket3, 2);
            resultList.add(ticket1); resultList.add(ticket2);
            resultList.add(ticket3);
        }
        return resultList;
    }
    public void generatefile() {
        System.out.println("Model RevenueReport: Đang khởi tạo thư viện xuất file...");
        System.out.println("Model RevenueReport: Đã đóng gói dữ liệu thành công!");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Ticket> getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(List<Ticket> toalRevenue) {
        this.totalRevenue = toalRevenue;
    }

    public String getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(String typeReport) {
        this.typeReport = typeReport;
    }
}
