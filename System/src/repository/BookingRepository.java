package repository;

import Interface.IRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Booking;
import util.LocalDateDeserializer;
import util.LocalDateSerializer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository implements IRepository<Booking, Integer> {

    private List<Booking> roomBookings;
    private static final String BOOKINGSFILE = "bookings.json";

    public BookingRepository() {
        this.roomBookings = new ArrayList<>();
    }

    public List<Booking> getRoomBookings() {
        return roomBookings;
    }


    @Override
    public boolean add(Booking booking) {

        return this.roomBookings.add(booking);
    }

    @Override
    public boolean delete(Integer id) {

        return roomBookings.remove(roomBookings.stream().filter(booking1 -> booking1.getId() == id));
    }

    @Override
    public Booking search(Integer id) {

        Booking booking = (Booking) roomBookings.stream().filter(booking1 -> booking1.getId() == id).findFirst().orElse(null);

        return booking;
    }

    @Override
    public void edit(Booking booking) {

        if (!this.roomBookings.isEmpty()) {

            for (Booking aux_booking : roomBookings) {

                if (aux_booking.getId() == booking.getId()) {

                    roomBookings.set(roomBookings.indexOf(aux_booking), booking);
                }
            }
        }
    }

    @Override
    public void writeGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {
        File file = new File(BOOKINGSFILE);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        gson.toJson(this.roomBookings, bufferedWriter);

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
            File file = new File(BOOKINGSFILE);
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(file));
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
            Gson gson = gsonBuilder.create();

            this.roomBookings = gson.fromJson(bufferedReader, new TypeToken<List<Booking>>() {
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