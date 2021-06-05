package util;

import model.Passenger;
import model.User;

import java.util.List;

public class Log {

    public static User logIn(String dni, String Password, List<User> users) {
        //bring data to memory from json

        User user = new Passenger(dni, Password);
        User returnUser = null;

        if (!users.isEmpty()) {

            for (User aux_user : users) {

                if ((aux_user.getDni().equals(user.getDni())) && (aux_user.getPassword().equals(user.getPassword()))) {

                    if (aux_user.getActive()) {

                        returnUser = aux_user;
                    }
                }
            }
        }

        return returnUser;
    }

}
