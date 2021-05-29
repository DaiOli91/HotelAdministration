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

    public String getUsersData() {
        return users.toString();
    }

    public List<User> getActiveEmployees(){
        List<User> activeEmployees = users
                .stream()
                .filter(user -> user instanceof Employee)
                .filter(employee -> ((Employee) employee)
                        .getActive())
                .collect(Collectors.toList());
        return activeEmployees;
    }

    public List<User> getFormerEmployees(){
        List<User> formerEmployees = users
                .stream()
                .filter(user -> user instanceof Employee)
                .filter(employee -> ((Employee) employee)
                        .getActive() == false)
                .collect(Collectors.toList());

        return formerEmployees;
    }

    public List<User> getActiveReceptionists(){
        List<User> activeReceptionist = users
                .stream()
                .filter(user -> user instanceof Receptionist)
                .filter(receptionist -> ((Receptionist) receptionist)
                        .getActive())
                .collect(Collectors
                        .toList());
        return activeReceptionist;
    }



    public List<User> getPassengers(){
        List<User> passengers = users
                .stream()
                .filter(user -> user instanceof Passenger)
                .collect(Collectors
                        .toList());
        return passengers;
    }


    // ╔═══════════════════════════════ End User Methods

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


/*
    public User logIn(User user) {
        //TODO fix log in


    }
*/

}
