package Controller;

import Model.PricingRule;

public class GiaVeController {
    public boolean savePriceConfig(String priceData) {
        PricingRule ruleEntity = new PricingRule();
        String timeRange = "30/04/2026 - 01/05/2026";
        boolean isOverlap = ruleEntity.checkTimeOverlap(priceData, timeRange);
        if (isOverlap) {
            return false;
        } else {
            ruleEntity.insertNewRule(priceData);
            return true;
        }

    }

}
