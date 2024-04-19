package utils.singleton;

import domain.model.Message;
import domain.model.Patient;
import domain.model.User;
import domain.model.UserRole;
import model.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IHMSDatabase {
    private static IHMSDatabase db;
    public List<User> users;
    public List<Users> usersMain;
    public List<Message> messages;
    public Users currentUser;
    public Users currentReceiver;
    public List<Patient> patients;
    private IHMSDatabase() {
        users= new ArrayList<>();
        messages = new ArrayList<>();
        currentReceiver = new Users("","", UserRole.MEMBER);
        currentUser=new Users("","", UserRole.MEMBER);
        patients= new ArrayList<>();
        usersMain = new ArrayList<>();
        users.add(new User("WASSANYI KEVIN", UUID.randomUUID().toString(), UserRole.MEMBER));
    }
    public static IHMSDatabase getInstance() {
        if (db == null) {
            db = new IHMSDatabase();
        }
        return db;
    }

}
