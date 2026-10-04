package Creational_Design_Pattern.Prototype_Design_Pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface EnemyPrototype{
    EnemyPrototype clone();
}

class Enemy implements EnemyPrototype{
    private String type;
    private int health;
    private double speed;
    private boolean armored;
    private String weapon;
    private List<String> inventory;

    Enemy(String type,int health,double speed,boolean armored,String weapon,List<String> inventory){
        this.type=type;
        this.health=health;
        this.speed=speed;
        this.armored=armored;
        this.weapon=weapon;
        this.inventory=new ArrayList<>(inventory);
    }

    @Override
    public Enemy clone(){
        return new Enemy(type,health,speed,armored,weapon,new ArrayList<>(inventory));
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void printStats() {
        System.out.println(type + " [Health: " + health +
                ", Speed: " + speed +
                ", Armored: " + armored +
                ", Weapon: " + weapon + "]");
    }
}

class EnemyRegistry{
    Map<String,Enemy> registry=new HashMap<>();

    public void register(String key,Enemy e){
        registry.put(key,e);
    }


    public Enemy getClone(String key){
        Enemy e = registry.get(key);
        if(e==null){
            throw  new IllegalArgumentException("No prototype registered");
        }
        return e.clone();
    }
}

public class Game_Enemy {
    public static void main(String[] args) {
        EnemyRegistry enemyRegistry=new EnemyRegistry();
        enemyRegistry.register("flying", new Enemy("FlyingEnemy", 100, 12.0, false,
                "Laser", new ArrayList<>(List.of("Speed Boost"))));
        enemyRegistry.register("armored", new Enemy("ArmoredEnemy", 300, 6.0, true,
                "Cannon", new ArrayList<>(List.of("Shield", "Helmet"))));

        Enemy e=enemyRegistry.getClone("flying");
        Enemy e2=enemyRegistry.getClone("armored");
        e.printStats();
        e2.printStats();
    }
}
