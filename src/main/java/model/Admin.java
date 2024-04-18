package model;

import domain.model.UserRole;
import utils.singleton.IHMSDatabase;

public class Admin extends Users{

    public Admin(String username, String email, UserRole userRole) {
        super(username, email, userRole);
    }
    public void addUser(Users user){
        IHMSDatabase.getInstance().usersMain.add(user);
    }
    public void removeUser(Users user){
        IHMSDatabase.getInstance().usersMain.remove(user);
    }
}
