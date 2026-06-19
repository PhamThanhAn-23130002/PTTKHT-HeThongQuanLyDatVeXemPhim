package Model;

public class InventoryItem {

    private String itemId;

    private String itemName;

    private int quantity;

    public InventoryItem(String itemId,
                         String itemName,
                         int quantity) {

        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}