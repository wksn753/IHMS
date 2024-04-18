package factory;

import domain.model.UserRole;
import model.Admin;
import model.Users;

public class UserMainFactory {
    public Users createUser(UserRole userRole,String userName,String email) {
        switch (userRole){
            case MEMBER -> {
                return new Users(userName,email,userRole);
            }

            case ADMIN -> {
                return new Admin(userName,email,userRole);
            }default -> {
                return new Users(userName,email,userRole);
            }
        }
    }

}
