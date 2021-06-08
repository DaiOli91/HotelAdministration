package exception;

public class UserIsAlreadyActive extends Exception{
    public UserIsAlreadyActive(){
        super("User account is already Active");
    }
}
