package SOLID;

//abstract class Vehicle{
//    abstract public void startEngine();
//}
//
//class Car extends Vehicle{
//    @Override
//    public void startEngine(){
//        System.out.println("Starting Car engine");
//    }
//}
//
//class Cycle extends Vehicle{ // this is not a perfect substitute for parent class
//    @Override
//    public void startEngine(){
//        throw new UnsupportedOperationException();
//    }
//}

abstract class Vehicle{
    abstract public void move();
}

abstract class EngineVehicle extends Vehicle{
    abstract public void startEngine();
}

abstract class NonEngineVehicle extends Vehicle{

}

class Car extends EngineVehicle{

    @Override
    public void startEngine() {
        System.out.println("Car engine starting");
    }

    @Override
    public void move() {
        System.out.println("Car moving");
    }
}

class Cycle extends NonEngineVehicle{

    @Override
    public void move() {
        System.out.println("Cycle moving");
    }
}

public class LSP {
}
