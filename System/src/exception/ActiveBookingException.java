package exception;

public class ActiveBookingException extends Exception {

    public ActiveBookingException() {
        super("There are active booking/s");
    }
}
