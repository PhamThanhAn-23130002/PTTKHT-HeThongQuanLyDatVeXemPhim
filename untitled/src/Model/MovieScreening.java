package Model;

import java.util.ArrayList;
import java.util.List;

public class MovieScreening {
    private String id;
    private String date;
    private Room room;
    private PricingRule priceRule;

    // UC-2.1: registry tĩnh để Controller tra cứu screeningId -> MovieScreening.
    private static List<MovieScreening> screenings = new ArrayList<>();

    public MovieScreening(String id, String date, Room room) {
        this.id = id;
        this.date = date;
        this.room = room;
        screenings.add(this);
    }

    public static MovieScreening findById(String screeningId) {
        if (screeningId == null) return null;
        for (MovieScreening s : screenings) {
            if (screeningId.equalsIgnoreCase(s.id)) return s;
        }
        return null;
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