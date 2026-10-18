package Behavioural_Design_Pattern.Mediator_Design_Pattern;

import java.util.ArrayList;
import java.util.List;

interface ChatMediator{
    void sendMessage(String message,User sender);
    void addUser(User user);
}

class ChatRoom implements ChatMediator{
    private List<User> usersList=new ArrayList<>();

     @Override
    public void sendMessage(String message, User sender) {
         for (User user : usersList) {
             if (user != sender) {
                 user.receiveMessage(message, sender.getName());
             }
         }
    }

    @Override
    public void addUser(User user) {
        usersList.add(user);
        System.out.println(user.getName() + " joined the chat.");
    }
}

abstract class User{
    protected ChatMediator chatMediator;
    protected String name;

    User(ChatMediator chatMediator, String name){
        this.chatMediator=chatMediator;
        this.name=name;
    }

    abstract void sendMessage(String message);
    abstract void receiveMessage(String message,String from);

    public String getName(){
        return name;
    }
}

class ChatUser extends User{

    ChatUser(ChatMediator chatMediator,String name){
        super(chatMediator,name);
    }

    @Override
    void sendMessage(String message) {
        System.out.println(name + " sends: " + message);
        this.chatMediator.sendMessage(message,this);
    }

    @Override
    void receiveMessage(String message, String from) {
        System.out.println(name + " receives from " + from + ": " + message);
    }
}

public class Chat_Room {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.sendMessage("Hey everyone!");
        System.out.println();
        bob.sendMessage("Hi Alice!");
    }
}
