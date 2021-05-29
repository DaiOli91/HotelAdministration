package Repository;

import Interface.IRepository;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository implements IRepository<User, String> {

    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }


    @Override
    public boolean add(User user) {

        return this.users.add(user);
    }

    @Override
    public boolean delete(String dni) {

        return users.remove((User) users.stream().filter(user -> user.getDni() == dni));
    }

    @Override
    public User search(String dni) {

        User returnUser = null;

        if (!this.users.isEmpty()) {

            for (User aux_user : users) {

                if (aux_user.getDni().equals(dni)) {

                    returnUser = aux_user;
                }
            }
        }
        return returnUser;
    }

    @Override
    public User edit(User user) {


        if (!this.users.isEmpty()) {

            for (User aux_user : users) {

                if (aux_user.getDni().equals(user.getDni())) {

                    user = users.set(users.indexOf(aux_user), user);
                }
            }
        }

        return user;
    }

}
