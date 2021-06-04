package repository;

import Interface.IRepository;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Availability;
import model.Booking;
import model.Category;
import model.Room;

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
        Gson gson = new Gson();

        for (Booking booking: roomBookings) {

            //gson.toJson(booking, booking.getClass(), bufferedWriter);
            gson.toJson(booking, Booking.class, bufferedWriter);
        }


    }

    @Override
    public void readGson() throws FileNotFoundException, IOException,  JsonIOException, JsonSyntaxException {
        File file = new File(BOOKINGSFILE);
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file));
        Gson gson = new Gson();
        //TODO manage exceptions
        //TODO condition to go through a file
        this.roomBookings = gson.fromJson(bufferedReader, new TypeToken<List<Booking>>() {}.getType());
    }
}
