import Controller.Hotel;
import Model.*;
import Utils.Log;


public class Main {
    public static void main(String[] args) {
        //menu here

        Hotel hotel1 = new Hotel();
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

        user1.setActive();
        hotel1.register(user1);

        User user2 = Log.LogIn("35892293", "pass456", hotel1.getUsers());
        if(user2 != null){
            System.out.println("User found: " + user2.toString());

        }


        //  System.out.println(hotel1.logInHotel(user1.getDni(), user1.getPassword()));
        //   System.out.println(hotel1.register(user1));
        //  System.out.println(hotel1.getUsersData());
        //  System.out.println(hotel1.logInHotel(user1.getDni(), user1.getPassword()));

        System.out.println("Former employee: " + hotel1.getFormerEmployees().toString());
    }
}
