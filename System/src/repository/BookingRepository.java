package repository;

import Interface.IRepository;
import model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository implements IRepository<Booking, Integer> {

    private List<Booking> roomBookings;

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

        Booking booking = (Booking) roomBookings.stream().filter(booking1 -> booking1.getId() == id);

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
}
