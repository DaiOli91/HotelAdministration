package Model;

import java.util.UUID;

public abstract class Employee extends User {

    protected UUID employeeNumber;

    public Employee(String dni, String firstName, String lastName, int age, Gender gender, String address, String telephone, String email, String password) {
        super(dni, firstName, lastName, age, gender, address, telephone, email, password);
        this.employeeNumber = UUID.randomUUID();
    }

    public String getEmployeeNumber() {
        return employeeNumber.toString();
    }

    @Override
    public String toString() {

        return super.toString() +
                "╠═ Employee Number: " + employeeNumber + '\n';
    }
}
