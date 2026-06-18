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
            Ticket mockTicket = new Student_OldTicket();
            resultList.add(mockTicket);
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
