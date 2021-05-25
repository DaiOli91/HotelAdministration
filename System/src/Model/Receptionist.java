package Model;

public class Receptionist extends Employee{
    private Shift shift;

    public Receptionist(String dni, String firstName, String lastName, int age, Gender gender, String address, String telephone, String email, String password, Shift shift) {
        super(dni, firstName, lastName, age, gender, address, telephone, email, password);
        this.shift = shift;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return super.toString() +
                "╠═ Role: Receptionist" + '\n' +
                "╠═ Shift: "+ shift + '\n' +
                "╚══════════════════════════════════════" + '\n';
    }
}
