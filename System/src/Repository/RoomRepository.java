package Repository;

import Interface.IRepository;
import Model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements IRepository<Room, Integer> {
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

    @Override
    public String add(Room room) {
        return "";
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Room search(Integer integer) {
        return null;
    }

    @Override
    public void edit(Room room) {

    }
}
