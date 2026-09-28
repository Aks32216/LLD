package OOPS;

interface Car{
    void brake();
    void accelarate();
    void rotateWheels();
}

class ElectricCarI implements Car{

    @Override
    public void brake() {
        System.out.println("braking in Electric car");
    }

    @Override
    public void accelarate() {
        System.out.println("Accelarating in electric car");
    }

    @Override
    public void rotateWheels() {
        System.out.println("rotating wheels in electric car");
    }
}

class FuelCarI implements Car{

    @Override
    public void brake() {
        System.out.println("braking in fuel car");
    }

    @Override
    public void accelarate() {
        System.out.println("Accelarating in fuel car");
    }

    @Override
    public void rotateWheels() {
        System.out.println("rotating wheels in fuel car");
    }
}

interface Payable{
    double calculatePay(); //public abstract
    double INTEREST_RATE=31.4;  // public static final since interface do not have instances so immutable attributes
    default void pay(){
        System.out.println("default paying method");
    }
}


interface t1{
    default void run(){
        System.out.println("Running in t1");
    }
}

interface t2{
    default void run(){
        System.out.println("Running in t2");
    }
}

class test implements t2{

}

public class Interface {
    public static void main(String[] args) {
        Car c = new ElectricCarI();
        c.accelarate();
        c.brake();
    }
}
