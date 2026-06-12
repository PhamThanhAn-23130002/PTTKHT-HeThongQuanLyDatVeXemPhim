package Controller;

public class GiaVeController {
    public boolean addPricingRule(String ruleName, String startTime, String endTime, double multiplier) {
        if (multiplier <= 0) {
            return false;
        }
        return true;
    }
}
