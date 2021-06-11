package util;

import controller.Hotel;
import exception.BookingNotFoundException;
import exception.DateValidationException;
import model.Passenger;
import model.User;

import java.io.IOException;
import java.util.List;

public class Log {

    public static User logIn(String dni, String Password, Hotel hotel) throws IOException {

        User user = new Passenger(dni, Password);
        User returnUser = null;

        if (hotel.getUsers().size() != 0) {

            returnUser = hotel.getUsers()
                    .stream()
                    .filter(user1 -> user1.getDni().equals(user.getDni()) && user1.getPassword().equals(user.getPassword()))
                    .findFirst().orElse(null);

            if (returnUser != null) {

                if (!returnUser.getActive()) {

                    returnUser = null;
                }
            }
            /*
            for (User aux_user : hotel.getUsers()) {

                if ((aux_user.getDni().equals(user.getDni())) && (aux_user.getPassword().equals(user.getPassword()))) {

                    if (aux_user.getActive()) {

                        returnUser = aux_user;
                    }
                }
            }
            */
        }

        return returnUser;
    }

}
