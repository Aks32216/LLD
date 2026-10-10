package Structural_Design_Pattern.Decorator_Design_Pattern;

interface Coffee{
    double getCost();
    void getDescription();
}

class SimpleCoffee implements Coffee{
    @Override
    public double getCost() {
        return 10;
    }

    @Override
    public void getDescription() {
        System.out.println("Plain cofee");
    }
}

abstract class CofeeDecorator implements Coffee{
    protected Coffee coffee;
    CofeeDecorator(Coffee coffee){
        this.coffee=coffee;
    }
}

class MilkDecorator extends CofeeDecorator{

    MilkDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public double getCost() {
        return 15 + coffee.getCost();
    }

    @Override
    public void getDescription() {
        System.out.println("Milk");
        coffee.getDescription();
    }
}

class SugarDecorator extends CofeeDecorator{
    SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return 20+coffee.getCost();
    }

    @Override
    public void getDescription() {
        System.out.println("Sugar");
        coffee.getDescription();
    }
}

class WhippedCreamDecorator extends CofeeDecorator{
    WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return 25+coffee.getCost();
    }

    @Override
    public void getDescription() {
        System.out.println("Whipped Cream");
        coffee.getDescription();
    }
}

public class Coffee_Shop {
    public static void main(String[] args) {
        Coffee coffee1=new WhippedCreamDecorator(new MilkDecorator(new SimpleCoffee()));
        coffee1.getDescription();
        System.out.println(coffee1.getCost());
    }
}
