package Behavioural_Design_Pattern.Command_Design_Pattern;

import java.util.Stack;

class Light{
    public void turnOn(){
        System.out.println("Light turned ON");
    }

    public void turnOff(){
        System.out.println("Light turned OFF");
    }
}

class Thermostat{
    private int currentTemp;

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        System.out.println("Thermostat set to " + currentTemp + "C");
        this.currentTemp = currentTemp;
    }
}

interface Command{
    void execute();
    void undo();
}

class LightOnCommand implements Command{
    private final Light light;

    LightOnCommand(Light light){
        this.light=light;
    }

    @Override
    public void execute() {
        this.light.turnOn();
    }

    @Override
    public void undo() {
        this.light.turnOff();
    }
}

class LightOffCommand implements Command{
    private final Light light;

    LightOffCommand(Light light){
        this.light=light;
    }


    @Override
    public void execute() {
        this.light.turnOff();
    }

    @Override
    public void undo() {
        this.light.turnOn();
    }
}

class SetTemperatureCommand implements Command{
    private final Thermostat thermostat;
    private int curTemp;
    private int lastTemp;

    SetTemperatureCommand(Thermostat thermostat,int temp){
        this.thermostat=thermostat;
        this.curTemp=temp;
    }

    @Override
    public void execute() {
        lastTemp=this.thermostat.getCurrentTemp();
        this.thermostat.setCurrentTemp(curTemp);
    }

    @Override
    public void undo() {
        this.thermostat.setCurrentTemp(lastTemp);
    }
}

class RemoteControl{
    private Stack<Command> history=new Stack<>();

    public void invokeCommand(Command command){
        history.push(command);
        command.execute();
    }

    public void undoCommand(){
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}



public class Smart_Home_Controller {
    public static void main(String[] args) {
        Light light = new Light();
        Thermostat thermostat = new Thermostat();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command setTemp = new SetTemperatureCommand(thermostat, 25);

        RemoteControl remote = new RemoteControl();

        System.out.println("--- Executing Commands ---");
        remote.invokeCommand(lightOn);
        remote.invokeCommand(setTemp);
        remote.invokeCommand(lightOff);

        System.out.println("\n--- Undoing Commands ---");
        remote.undoCommand();
        remote.undoCommand();
        remote.undoCommand();
        remote.undoCommand();
    }
}
