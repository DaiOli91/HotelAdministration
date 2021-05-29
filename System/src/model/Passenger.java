package model;

public class Passenger extends User{

    private String origin;

    public Passenger(String dni, String firstName, String lastName, int age, Gender gender, String address, String telephone, String email, String password, String origin) {
        super(dni, firstName, lastName, age, gender, address, telephone, email, password);
        this.origin = origin;
    }

    public Passenger(String dni, String password) {
        super(dni, password);
        this.origin = "";
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return super.toString() +
                "╠═  Origin: " + origin + '\n' +
                "╚══════════════════════════════════════" + '\n';
    }
}
