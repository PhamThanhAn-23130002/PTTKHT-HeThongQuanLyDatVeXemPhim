package Model;

public class MovieScreening {
    private String id;
    private String date;
    private Room room;
    private PricingRule priceRule;

    public MovieScreening(String id, String date, Room room) {
        this.id = id;
        this.date = date;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public PricingRule getPriceRule() {
        return priceRule;
    }

    public void setPriceRule(PricingRule priceRule) {
        this.priceRule = priceRule;
    }
}