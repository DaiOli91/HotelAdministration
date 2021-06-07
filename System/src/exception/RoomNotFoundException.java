package exception;

public class RoomNotFoundException extends Exception {

    public RoomNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
