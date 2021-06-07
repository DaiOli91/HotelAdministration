package repository;

import Interface.IRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Room;

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
    public void edit(Room room) {

        if (!this.rooms.isEmpty()) {

            for (Room aux_room : rooms) {

                if (aux_room.getNumber() == room.getNumber()) {

                    rooms.set(rooms.indexOf(aux_room), room);
                }
            }
        }
    }

    @Override
    public void writeGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {
        File file = new File(ROOMSFILE);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        gson.toJson(this.rooms, bufferedWriter);

        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException fileNotFound) {
            throw new FileNotFoundException();
        } catch (JsonIOException jsonIo) {
            throw new JsonIOException(jsonIo);
        } catch (JsonSyntaxException jsonSyntax) {
            throw new JsonSyntaxException(jsonSyntax);
        }
    }

    @Override
    public void readGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {

        try {
            File file = new File(ROOMSFILE);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Gson gson = new Gson();
            this.rooms = gson.fromJson(bufferedReader, new TypeToken<List<Room>>() {
            }.getType());

            bufferedReader.close();

        } catch (FileNotFoundException fileNotFound) {
            throw new FileNotFoundException();
        } catch (JsonIOException jsonIo) {
            throw new JsonIOException(jsonIo);
        } catch (JsonSyntaxException jsonSyntax) {
            throw new JsonSyntaxException(jsonSyntax);
        }
    }
}

