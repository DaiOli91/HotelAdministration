package Model;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
    NA("N/A");

    private String gender;

    private Gender(String gender) {

        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
