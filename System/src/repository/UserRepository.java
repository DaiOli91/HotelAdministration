package repository;

import Interface.IRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IRepository<User, String> {

    private List<User> users;
    private static final String PASSANGERSFILE = "passengers.json";
    private static final String RECEPTIONISTSFILE = "receptionists.json";
    private static final String MANAGERSFILE = "managers.json";


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

    @Override
    public void writeGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {
        List<User> passengers = new ArrayList<>();
        List<User> managers = new ArrayList<>();
        List<User> receptionists = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Passenger) {
                passengers.add(user);
            } else if (user instanceof Receptionist) {
                receptionists.add(user);
            } else if (user instanceof Manager) {
                managers.add(user);
            }

        }
        try {
            File filePassengers = new File(PASSANGERSFILE);
            File fileReceptionists = new File(RECEPTIONISTSFILE);
            File fileManagers = new File(MANAGERSFILE);

            BufferedWriter bwPassengers = new BufferedWriter(new FileWriter(filePassengers));
            BufferedWriter bwReceptionists = new BufferedWriter(new FileWriter(fileReceptionists));
            BufferedWriter bwManagers = new BufferedWriter(new FileWriter(fileManagers));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(passengers, bwPassengers);
            gson.toJson(receptionists, bwReceptionists);
            gson.toJson(managers, bwManagers);

            bwManagers.close();
            bwPassengers.close();
            bwReceptionists.close();

        } catch (FileNotFoundException fileNotFound) {
            throw fileNotFound;
        } catch (JsonIOException jsonIo) {
            throw jsonIo;
        } catch (JsonSyntaxException jsonSyntax) {
            throw jsonSyntax;
        }

    }

    @Override
    public void readGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException {
        try {
            File filePassengers = new File(PASSANGERSFILE);
            File fileReceptionists = new File(RECEPTIONISTSFILE);
            File fileManagers = new File(MANAGERSFILE);
            BufferedReader brPassengers = new BufferedReader(new FileReader(filePassengers));
            BufferedReader brReceptionists = new BufferedReader(new FileReader(fileReceptionists));
            BufferedReader brManagers = new BufferedReader(new FileReader(fileManagers));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();


            this.users = gson.fromJson(brPassengers, new TypeToken<List<Passenger>>() {
            }.getType());
            this.users.addAll(gson.fromJson(brManagers, new TypeToken<List<Manager>>() {
            }.getType()));
            this.users.addAll(gson.fromJson(brReceptionists, new TypeToken<List<Receptionist>>() {
            }.getType()));

            brManagers.close();
            brPassengers.close();
            brReceptionists.close();

        } catch (FileNotFoundException fileNotFound) {
            throw fileNotFound;
        } catch (JsonIOException jsonIo) {
            throw jsonIo;
        } catch (JsonSyntaxException jsonSyntax) {
            throw jsonSyntax;
        }
    }


}
