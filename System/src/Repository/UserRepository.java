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
    //TODO Password should be 10 characters long
    public String add(User user) {
        String message = "User has been added successfully";
        if (this.users.isEmpty()) {
            this.addSuperAdmin();
            this.users.add(user);
        } else if (this.users.contains(user)) {
            message = "User already exists";
        } else this.users.add(user);

        return message;
    }

    public String addEmployee(Employee employee){
        String message = "User has been added successfully";
        if (this.users.isEmpty()) {
            this.addSuperAdmin();
            this.users.add(employee);
        } else if (this.users.contains(employee)) {
            message = "Employee already exists";
        } else this.users.add(employee);

        return message;
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

    public void addSuperAdmin() {
        User superAdmin = new Manager("11111111"
                , "Super"
                , "Admin"
                , 100
                , Gender.FEMALE
                , "Something 123"
                , "111111111"
                , "superadmin@mail.com"
                , "superadmin123");

        this.users.add(superAdmin);
    }

    public User logIn(User user) {
        //TODO fix log in
        User returnUser = null;
        if (!this.users.isEmpty()) {
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

    public void userHC() {
        addSuperAdmin();
        this.users.add(new Passenger("14785969"
                , "Andrea"
                , "Carrizo"
                , 60
                , Gender.FEMALE
                , "Gaboto 5646"
                , "4585858"
                , "something@asomething.com"
                , "pass123"
                , "Mar del Plata"));

        this.users.add(new Passenger("14874623"
                , "Felipe"
                , "Graziano"
                , 60
                , Gender.MALE
                , "Mexico 3536"
                , "4651236"
                , "something@something.com"
                , "pass123"
                , "San Clemente"));

        this.users.add(new Receptionist("18956565"
                , "Sofia"
                , "Caceres"
                , 58
                , Gender.FEMALE
                , "Bahia Blanca 123"
                , "2235686968"
                , "soficaceres@gmail.com"
                , "sofi1966"
                , Shift.MORNING));

        this.users.add(new Receptionist("35236598"
                , "Santiago"
                , "Gonzalez"
                , 30
                , Gender.MALE
                , "Rio Negro 456"
                , "2235456985"
                , "santig@gmail.com"
                , "santi30"
                , Shift.NIGHT));

        this.users.add(new Manager("13525252"
                , "Calos"
                , "Patriarcado"
                , 61
                , Gender.MALE
                , "Patagones 6000"
                , "2235505065"
                , "carlos.patriarcado@gmail.com"
                , "patriarcado123"));

        this.users.add(new Manager("25525252"
                , "Natalia"
                , "Bossy"
                , 40
                , Gender.FEMALE
                , "San Luis 4052"
                , "2235235689"
                , "natybossy@gmail.com"
                , "bossy123"));

    }
}
