package Model;

public class Manager extends Employee {


    public Manager(String dni, String firstName, String lastName, int age, Gender gender, String address, String telephone, String email, String password) {
        super(dni, firstName, lastName, age, gender, address, telephone, email, password);
    }

    @Override
    public String toString() {
        return super.toString() +
                "╠═ Role: Manager" + '\n' +
                "╚══════════════════════════════════════";
    }
}
