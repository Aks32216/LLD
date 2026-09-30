package Creational_Design_Pattern;

interface Notification{
    void send(String message);
}

class EmailNotification implements Notification{
    public void send(String message){
        System.out.println("send email "+message);
    }
}

class SMSNotification implements Notification{
    @Override
    public void send(String message) {
        System.out.println("Send SMS "+message);
    }
}

//class NotificationManager{ // simple factory. Does not follow ocp. whenever some new notification type comes we need to change this class
//    public static Notification createNotification(String type){
//        switch (type){
//            case "EMAIL": return new EmailNotification();
//            case "SMS": return new SMSNotification();
//            default: throw new IllegalArgumentException();
//        }
//    }
//}

abstract class NotificationManager{
    public void send(String message){
        Notification n=createNotification();
        n.send(message);
    }

    protected abstract Notification createNotification();
}

class EmailNotificationManager extends NotificationManager{

    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}

class SMSNotificationManager extends NotificationManager{

    @Override
    protected Notification createNotification() {
        return new SMSNotification();
    }
}



public class NotificationSystem {
    public static void main(String[] args) {
        NotificationManager nm=new EmailNotificationManager();
        nm.send("Hello");
        nm=new SMSNotificationManager();
        nm.send("BYE");
    }
}
