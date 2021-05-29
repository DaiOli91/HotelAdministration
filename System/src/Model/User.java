package Model;

public abstract class User {

    protected String dni;
    protected String firstName;
    protected String lastName;
    protected int age;
    protected Gender gender;
    protected String address;
    protected String telephone;
    protected String email;
    protected String password;
    protected Boolean active;


    public User(String dni, String firstName, String lastName, int age, Gender gender, String address, String telephone, String email, String password) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.active = true;
    }

    public User(String dni, String password) {
        this.dni = dni;
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
        this.gender = Gender.FEMALE;
        this.address = "";
        this.telephone = "";
        this.email = "";
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive() {
        this.active = !active;
    }

    @Override
    public String toString() {
        return "╔═  User Name: " + firstName + " " + lastName + "   ══════════════════════════" + '\n' +
                "╠═ DNI: " + dni + '\n' +
                "╠═ Age:" + age + '\n' +
                "╠═ Gender:" + gender + '\n' +
                "╠═ Address: " + address + '\n' +
                "╠═ Telephone: " + telephone + '\n' +
                "╠═ Email: " + email + '\n';
    }
}
