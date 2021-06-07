package exception;

public class UserNotFoundException extends Exception {

    // TODO Ask Orellano about exception messages.
    /*
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    */
    public UserNotFoundException() {
        super("User not found");
    }
}
