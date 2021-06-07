package exception;

public class BookingNotFoundException extends Exception {

    public BookingNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
