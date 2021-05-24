package Model;

import java.util.UUID;

public abstract class Employee extends User{
    protected UUID employeeNumber;
    protected Boolean active; //to differenciate former employees- true by default

    public Employee(String dni, String firstName, String lastName, int age, Gender gender, String address, String telephone, String email, String password) {
        super(dni, firstName, lastName, age, gender, address, telephone, email, password);
        this.employeeNumber = UUID.randomUUID();
        this.active = true;
    }

    public String getEmployeeNumber() {
        return employeeNumber.toString();
    }

    public void setEmployeeNumber() {
        this.employeeNumber = UUID.randomUUID();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive() {
        this.active = !getActive();
    }

    @Override
    public String toString() {
        return super.toString() +
                "╠═ Employee Number: " + employeeNumber + '\n' +
                "╠═ In activity:" + active +  '\n';

    }
}
