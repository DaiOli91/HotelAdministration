package exception;

public class ReceptionistShiftNeedsChange extends Exception {

    public ReceptionistShiftNeedsChange() {
        super("The receptionist shift needs to be changed");
    }

}
