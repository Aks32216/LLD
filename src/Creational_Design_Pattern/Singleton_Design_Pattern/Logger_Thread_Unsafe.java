package Creational_Design_Pattern.Singleton_Design_Pattern;

class Logger{
    private static Logger instance;
    private Logger(){};

    public static Logger getInstance(){
        if(instance==null){
            instance=new Logger();
        }
        return instance;
    }

    public void log(String message){
        System.out.println(message);
    }
}

public class Logger_Thread_Unsafe {
    public static void main(String[] args) {
        Logger logger=Logger.getInstance();
        logger.log("Data created");
    }
}
