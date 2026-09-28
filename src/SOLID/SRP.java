package SOLID;

//class BreadBaker { // A class having much responsibility hence difficult to maintain/read
//    public
//    void bakeBread() { System.out.println("Baking high-quality bread..."); }
//
//    public
//    void manageInventory() { System.out.println("Managing inventory..."); }
//
//    public
//    void orderSupplies() { System.out.println("Ordering supplies..."); }
//
//    public
//    void serveCustomer() { System.out.println("Serving customers..."); }
//
//    public
//    void cleanBakery() { System.out.println("Cleaning the bakery..."); }
//
//    public
//    static void main(String[] args) {
//        BreadBaker baker = new BreadBaker();
//        baker.bakeBread();
//        baker.manageInventory();
//        baker.orderSupplies();
//        baker.serveCustomer();
//        baker.cleanBakery();
//    }
//}

class BreadBaker {
    public
    void bakeBread() { System.out.println("Baking high-quality bread..."); }
}

class InventoryManager {
    public
    void manageInventory() { System.out.println("Managing inventory..."); }
}

class SupplyOrder {
    public
    void orderSupplies() { System.out.println("Ordering supplies..."); }
}

class CustomerService {
    public
    void serveCustomer() { System.out.println("Serving customers..."); }
}

class BakeryCleaner {
    public
    void cleanBakery() { System.out.println("Cleaning the bakery..."); }
}

public class SRP {

    // Each class focuses on its specific responsibilites
    public static void main(String[] args) {
        BreadBaker baker = new BreadBaker();
        InventoryManager inventoryManager = new InventoryManager();
        SupplyOrder supplyOrder = new SupplyOrder();
        CustomerService customerService = new CustomerService();
        BakeryCleaner cleaner = new BakeryCleaner();

        baker.bakeBread();
        inventoryManager.manageInventory();
        supplyOrder.orderSupplies();
        customerService.serveCustomer();
        cleaner.cleanBakery();

    }
}
