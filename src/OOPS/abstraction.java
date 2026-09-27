package OOPS;

abstract class car{
    static void rotateWheels(){
        System.out.println("Rotating wheels");
    }
    abstract void start();
    abstract void brake();
}

class FuelCar extends car{
    @Override
    void start(){
        System.out.println("Starting fuel car");
    }

    @Override
    void brake(){
        System.out.println("Braking fuel car");
    }
}

class ElectricCar extends car{
    @Override
    void brake() {
        System.out.println("Starting elec car");
    }

    @Override
    void start(){
        System.out.println("Braking elec car");
    }
}

public class abstraction {

    static void runCar(car c){
        c.start();
    }

    static void stopCar(car c){
        c.brake();
    }

    public static void main(String[] args) {
        FuelCar fc=new FuelCar();
        ElectricCar ec= new ElectricCar();

        fc.rotateWheels();
        ec.rotateWheels();
        car.rotateWheels();


        runCar(fc);
        runCar(ec);
        stopCar(ec);
        stopCar(fc);
    }
}
