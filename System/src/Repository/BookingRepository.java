package Repository;

import Interface.IRepository;
import Model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository /*implements IRepository<Booking>*/ {
    private List<Booking> roomBookings;

    public BookingRepository() {
        this.roomBookings = new ArrayList<>();
    }

    public List<Booking> getRoomBookings() {
        return roomBookings;
    }
    public String getRoomBookingsData() {
        return roomBookings.toString();
    }
}
