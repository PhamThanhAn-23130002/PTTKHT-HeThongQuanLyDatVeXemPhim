package Model;

import java.util.List;

public class Room {
    private String id;
    private List<Seat> seatList;
    private boolean status;

    public Room(String id, List<Seat> seatList, boolean status) {
        this.id = id;
        this.seatList = seatList;
        this.status = status;
    }

    public String getId() { return id; }
    public List<Seat> getSeatList() { return seatList; }
}