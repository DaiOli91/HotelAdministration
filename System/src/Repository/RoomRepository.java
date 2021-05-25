package Repository;

import Interface.IRepository;
import Model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class RoomRepository implements IRepository<Room, Integer> {

    private List<Room> rooms;

    public RoomRepository() {
        this.rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String add(Room room) {

        rooms.add(room);
        return "The room was added correctly";
    }

    @Override
    public void delete(Integer roomNumber) {

        rooms.remove((Room) rooms.stream().filter(room1 -> room1.getNumber() == roomNumber));
    }

    @Override
    public Room search(Integer roomNumber) {

        Room room = (Room) rooms.stream().filter(room1 -> room1.getNumber() == roomNumber);
        return room;
    }

    @Override
    public void edit(Room room) {

        System.out.println("No se que carajo hacer aca. La clase no se edita y la chupan todos. Odio la facultad");
    }

    public String getRoomsData() {
        return rooms.toString();
    }
}
