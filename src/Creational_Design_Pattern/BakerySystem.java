package Creational_Design_Pattern;


interface Pizza{
    public void bake();
}

class Margareetha implements Pizza{
    public void bake(){
        System.out.println("baking Margareetha");
    }
}

class FarmHouse implements Pizza{
    public void bake(){
        System.out.println("Baking farmhouse");
    }
}

abstract class Bakery{
    public void bake(){
        Pizza p=createBakery();
        p.bake();
    }
    protected abstract Pizza createBakery();
}

class MargareethaBakery extends Bakery{
    public Pizza createBakery(){
        return new Margareetha();
    }
}

class FarmhouseBakery extends Bakery{
    public Pizza createBakery(){
        return new FarmHouse();
    }
}


public class BakerySystem {
    public static void main(String[] args) {
        Bakery bk=new MargareethaBakery();
        bk.bake();
        bk=new FarmhouseBakery();
        bk.bake();
    }
}
