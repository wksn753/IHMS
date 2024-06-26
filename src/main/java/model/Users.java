package model;

import domain.model.UserRole;

import java.util.UUID;

public class Users {
    private String name;
    private String email;
    private UserRole userRole;
    private String id;
    public Users(String username, String email, UserRole userRole){
        this.name=username;
        this.email=email;
        this.userRole=userRole;
        this.id= UUID.randomUUID().toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public  String toString(){
        return name.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
