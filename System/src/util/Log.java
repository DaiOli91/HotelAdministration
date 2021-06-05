package util;

import controller.Hotel;
import model.Passenger;
import model.User;

import java.io.IOException;
import java.util.List;

public class Log {

    public static User logIn(String dni, String Password, Hotel hotel) {
        try {
            hotel.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = new Passenger(dni, Password);
        User returnUser = null;

        if (!hotel.getUsers().isEmpty()) {

            for (User aux_user : hotel.getUsers()) {

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
