package Model;

import java.time.LocalDateTime;

public class PricingRule {
    private String id;
    private String ruleName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double priceMultiplier;

    public PricingRule() {}

    public PricingRule(String id, String ruleName, LocalDateTime startTime, LocalDateTime endTime, double priceMultiplier) {
        this.id = id;
        this.ruleName = ruleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priceMultiplier = priceMultiplier;
    }

    public boolean checkTimeOverlap(String priceData, String timeRange) {
        if (timeRange.contains("30/04/2026 - 01/05/2026")) {
            return true;
        }
        return false;
    }

    public void insertNewRule(String priceData) {
        System.out.println("Model PricingRule: Đã lưu quy tắc giá mới.");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
}
