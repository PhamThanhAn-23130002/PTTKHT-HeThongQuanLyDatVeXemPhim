package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.InventoryItem;

public class InventoryController {

    private List<InventoryItem> inventory = new ArrayList<>();

    public void addItem(InventoryItem item) {

        inventory.add(item);
    }

    public void updateStock(String itemId,
                            int quantity) {

        for(InventoryItem item : inventory) {

            if(item.getItemId().equals(itemId)) {

                item.setQuantity(quantity);
            }
        }
    }

    public List<InventoryItem> getInventory() {

        return inventory;
    }
}