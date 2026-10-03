package Creational_Design_Pattern.Factory_Design_Pattern;

/*
Problem Statement:
A logistics company plans deliveries. Initially road transport(truck), later sea(Ship)
and air(Plane). The planning logic (planDelivery()) is identical regardless of transport;
only which transport gets creted differs. Design so that adding a transport mode doesn't
touch the planning code.
*/

interface Transport{
    public void planDelivery();
}

class LandTransport implements Transport{
    @Override
    public void planDelivery() {
        System.out.println("Planning logistic delivery by land");
    }
}

class SeaTransport implements Transport{
    @Override
    public void planDelivery() {
        System.out.println("Planning logistic delivery by sea");
    }
}

abstract class TransportCreator{
    abstract public Transport createTransport();

    public void planDelivery(){
        Transport transport=createTransport();
        transport.planDelivery();
    }
}

class LandTransportCreator extends TransportCreator{
    @Override
    public Transport createTransport() {
        return new LandTransport();
    }
}

class SeaTransportCreator extends TransportCreator{
    @Override
    public Transport createTransport() {
        return new SeaTransport();
    }
}

public class Logistics {
    public static void main(String[] args) {
        TransportCreator transportCreator = new LandTransportCreator();
        transportCreator.planDelivery();
        transportCreator=new SeaTransportCreator();
        transportCreator.planDelivery();
    }
}
