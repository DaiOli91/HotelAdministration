package util;

import model.Passenger;
import model.User;

import java.util.List;

public class Log {

    public static User LogIn(String dni, String Password, List<User> users) {
        //bring data to memory from json

        User user = new Passenger(dni, Password);
        User returnUser = null;

        if (!users.isEmpty()) {

            for (User aux_user : users) {

                if ((aux_user.getDni().equals(user.getDni())) && (aux_user.getPassword().equals(user.getPassword()))) {

                    returnUser = aux_user;
                }
            }
        }

        return returnUser;
    }
/*
    public void LogOut(User user){
      //logic here, save data in json to exit program
    }
    */

}
