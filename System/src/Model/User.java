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
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
