package Creational_Design_Pattern.Singleton_Design_Pattern;

class Logger2{
    private static Logger2 instance;
    private Logger2(){};

    public static Logger2 getInstance(){
        if(instance==null){
            synchronized (Logger2.class){
                if(instance==null){
                    instance=new Logger2();
                }
            }
        }
        return instance;
    }

    public void log(String message){
        System.out.println(message);
    }
}

public class Logger_Double_Locking {
    public static void main(String[] args) {
        Logger2 logger2=Logger2.getInstance();
        logger2.log("Hi there");
    }
}
