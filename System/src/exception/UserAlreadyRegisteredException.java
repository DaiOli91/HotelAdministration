package exception;

public class UserAlreadyRegisteredException extends Exception{
    public UserAlreadyRegisteredException() {
        super("This user id is already registered.");
    }
}
