package repository;

import Interface.IRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Availability;
import model.Category;
import model.Room;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements IRepository<Room, Integer> {

    private List<Room> rooms;
    private static final String ROOMSFILE = "rooms.json";

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

    @Override
    public void writeGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {
        File file = new File(ROOMSFILE);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        // Writer writer = Files.newBufferedWriter(Paths.get(ROOMSFILE));
        Gson gson = new Gson();
        //Gson gson = new GsonBuilder().create();

        gson.toJson(this.rooms, bufferedWriter);

        //   gson.toJson(rooms, new FileWriter(ROOMSFILE));
      /*  for (Room room : rooms) {
            gson.toJson(room, Room.class, bufferedWriter);
        }*/
        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void readGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {
        try {
            File file = new File(ROOMSFILE);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Gson gson = new Gson();
            this.rooms = gson.fromJson(bufferedReader, new TypeToken<List<User>>() {
            }.getType());

            //TODO try this...may go terribly wrong...

            try {
                bufferedReader.close();
            } catch (IOException ioe) {

                throw ioe;
            }
        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        }

    }
}
