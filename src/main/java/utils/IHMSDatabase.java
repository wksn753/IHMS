package utils;

import domain.model.Message;
import domain.model.Patient;
import domain.model.User;
import domain.model.UserRole;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IHMSDatabase {
    private static IHMSDatabase db;
    public List<User> users;
    public List<Message> messages;
    public User currentUser;
    public User currentReceiver;
    public List<Patient> patients;
    public List<Message> userMessages;
    public List<Message> userInbox;
    private IHMSDatabase() {
        users= new ArrayList<>();
        messages = new ArrayList<>();
        userMessages = new ArrayList<>();
        userInbox= new ArrayList<>();
        currentReceiver = new User("","", UserRole.MEMBER);
        currentUser=new User("","", UserRole.MEMBER);
        patients= new ArrayList<>();
        users.add(new User("WASSANYI KEVIN", UUID.randomUUID().toString(), UserRole.MEMBER));
    }
    public static IHMSDatabase getInstance() {
        if (db == null) {
            db = new IHMSDatabase();
        }
        return db;
    }
    public static @NotNull List<Message> getAllUserMessage(){
        List<Message> messageList = new ArrayList<>();
        for(Message msg:db.messages){
            if((msg.getReceiverId().equals(db.currentUser.getId())||(msg.getSenderId().equals(db.currentUser.getId())))){
                messageList.add(msg);
            }
        }
        return messageList;
    }
    public static @NotNull List<Message> getSpecificMessageList(){
        List<Message> messageList = new ArrayList<>();
        for(Message msg:IHMSDatabase.getAllUserMessage()){
            if((msg.getReceiverId().equals(db.currentReceiver.getId())||(msg.getSenderId().equals(db.currentReceiver.getId())))){
                messageList.add(msg);
            }
        }
        return messageList;
    }

}
