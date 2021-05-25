import Controller.Hotel;
import Model.*;


public class Main {
    public static void main(String[] args) {
        Hotel hotel1 = new Hotel();
        hotel1.userHC();
        //System.out.println(hotel1.getUsersData());
        User user1 = new Manager("35892293"
                , "Daiana"
                , "Olivera"
                , 30
                , Gender.FEMALE
                , "Solis 5526"
                , "2235566592"
                , "oliveratup@gmail.com"
                , "pass456");

        System.out.println(hotel1.logIn(user1));
        System.out.println(hotel1.register(user1));
        System.out.println(hotel1.getUsersData());
        System.out.println(hotel1.logIn(user1));

    }
}
