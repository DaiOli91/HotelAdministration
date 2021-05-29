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
    public String add(User user) {
        this.users.add(user);
        return "User successfully added";
    }

    @Override
    public void delete(String s) {
        if(!this.users.isEmpty()){
            for (User aux_user : users
                 ) {
                if (aux_user.getDni().equals(s)){
                    users.remove(aux_user);
                }

            }
        }
    }

    @Override
    public User search(String s) {
        User returnUser = null;
        if (!this.users.isEmpty()) {
            for (User aux_user : users
            ) {
                if (aux_user.getDni().equals(s)) {
                    returnUser = aux_user;
                }

            }
        }
        return returnUser;
    }

    @Override
    public void edit(User user) {
        if (!this.users.isEmpty()) {
            for (User aux_user : users
            ) {
                if (aux_user.getDni().equals(user.getDni())) {
                    users.add(users.indexOf(aux_user), user);
                }

            }
        }

    }

}
