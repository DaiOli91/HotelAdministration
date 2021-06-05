package repository;

import Interface.IRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Availability;
import model.Booking;
import model.Category;
import model.Room;
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
    public Booking edit(Booking booking) {

        if (!this.roomBookings.isEmpty()) {

            for (Booking aux_booking : roomBookings) {

                if (aux_booking.getId() == booking.getId()) {

                    booking = roomBookings.set(roomBookings.indexOf(aux_booking), booking);

                }
            }
        }

        return booking;
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
        } catch (IOException e) {
            //e.printStackTrace();
            throw e;
        }

    }

    @Override
    public void readGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {
        File file = new File(BOOKINGSFILE);
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.create();
        //TODO manage exceptions
        this.roomBookings = gson.fromJson(bufferedReader, new TypeToken<List<Booking>>() {
        }.getType());

        try {
            bufferedReader.close();
        }
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (IOException ioe) {

            throw ioe;
        }

        }


}
