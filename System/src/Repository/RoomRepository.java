package Repository;

import Interface.IRepository;
import Model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository /*implements IRepository<Room>*/ {
    private List<Room> rooms;

    public RoomRepository() {
        this.rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public String getRoomsData() {
        return rooms.toString();
    }

}
