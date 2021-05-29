package Model;

import java.util.List;

public class Log {

    public User LogIn(String dni, String Password, List<User> users){
        User user = new Passenger(dni, Password);
        User returnUser = null;
        if (users.isEmpty()) {
            for (User aux_user : users
            ) {
                if ((aux_user.getDni().equals(user.getDni()))
                        && (aux_user.getPassword().equals(user.getPassword()))) {
                    returnUser = aux_user;

                }

            }
        }
        return returnUser;
    }
}
