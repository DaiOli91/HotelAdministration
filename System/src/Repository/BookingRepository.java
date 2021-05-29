package Repository;

import Interface.IRepository;
import Model.Booking;
import Model.Room;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository implements IRepository<Booking, Integer>
{
    private List<Booking> roomBookings;

    public BookingRepository() {
        this.roomBookings = new ArrayList<>();
    }

    public List<Booking> getRoomBookings() {
        return roomBookings;
    }


    @Override
    public String add(Booking booking) {
        this.roomBookings.add(booking);
        return "Booking successfully created";
    }

    @Override
    public void delete(Integer id) {
        roomBookings.remove(roomBookings.stream().filter(booking1 -> booking1.getId() == id));

    }

    @Override
    public Booking search(Integer id) {
        Booking booking = (Booking) roomBookings.stream().filter(booking1 ->booking1.getId() == id);
        return booking;
    }

    @Override
    public void edit(Booking booking) {
        if (!this.roomBookings.isEmpty()) {
            for (Booking aux_booking : roomBookings
            ) {
                if (aux_booking.getId() == booking.getId()) {
                    roomBookings.add(roomBookings.indexOf(aux_booking), booking);
                }

            }
        }
    }
}
