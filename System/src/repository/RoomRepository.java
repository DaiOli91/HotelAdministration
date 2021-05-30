package repository;

import Interface.IRepository;
import model.Room;

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

    @Override
    public boolean add(Room room) {

        return rooms.add(room);
    }

    @Override
    public boolean delete(Integer roomNumber) {

        return rooms.remove((Room) rooms.stream().filter(room1 -> room1.getNumber() == roomNumber));
    }

    @Override
    public Room search(Integer roomNumber) {

        Room room = (Room) rooms.stream().filter(room1 -> room1.getNumber() == roomNumber).findFirst().orElse(null);

        return room;
    }

    @Override
    public Room edit(Room room) {

        if (!this.rooms.isEmpty()) {

            for (Room aux_room : rooms) {

                if (aux_room.getNumber() == room.getNumber()) {

                    room = rooms.set(rooms.indexOf(aux_room), room);
                }
            }
        }

        return room;
    }

}
