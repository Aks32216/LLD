/**
 * Topic   : SOLID Principles
 * Concept : Single Responsibility Principle (SRP)
 *
 * Definition:
 *  A class should have only ONE reason to change.
 *  "Reason to change" = the stakeholder/actor whose requirement drives that change.
 *
 * The Problem — God Class:
 *  OrderService was doing 4 unrelated jobs:
 *    1. Inventory check
 *    2. Order processing + ID generation
 *    3. Inventory update
 *    4. Email notification
 *  Any change to email format, pricing logic, or stock rules forced touching
 *  the same class — high coupling, hard to test, hard to reuse.
 *
 * The Fix — Split by responsibility:
 *  - InventoryManager  → owns stock data; only changes if stock logic changes
 *  - NotificationService → owns email format; only changes if comms method changes
 *  - OrderProcessor    → owns the workflow; only changes if order flow changes
 *  - Order             → owns order data shape; only changes if data model changes
 *
 * Key C++ detail — store collaborators as reference members:
 *  If OrderProcessor stored InventoryManager by value, it would work on a copy
 *  and the original inventory in main() would never see stock deductions.
 *  Storing as InventoryManager& means OrderProcessor operates on the real object.
 *  Side effect: reference members delete the copy-assignment operator — good,
 *  because two processors sharing one inventory accidentally would be a bug.
 *
 * How to identify SRP violations:
 *  - Class has methods that change for unrelated reasons
 *  - Class name contains "And", "Manager", "Helper", "Service" doing too much
 *  - Unit-testing one feature requires setting up unrelated dependencies
 *
 * SRP does NOT mean one method per class.
 *  InventoryManager can have isItemAvailable() AND updateInventoryItem() —
 *  both relate to the same responsibility (managing stock).
 */

// ─── Driver ───────────────────────────────────────────────────────────────────

#include <iostream>
#include <iomanip>
#include <string>
#include <map>
#include <vector>

using namespace std;

// Before: One class doing three unrelated jobs
class OrderService {
private:
    map<string, int> inventory;
    vector<string> orders;

public:
    OrderService() {
        inventory["LAPTOP"] = 10;
        inventory["PHONE"] = 25;
        inventory["TABLET"] = 15;
    }

    void placeOrder(const string& productId, int quantity,
                    const string& customerEmail) {
        // Responsibility 1: Inventory check
        auto it = inventory.find(productId);
        if (it == inventory.end() || it->second < quantity) {
            cout << "Insufficient stock for " << productId << endl;
            return;
        }

        // Responsibility 2: Order processing
        double pricePerUnit = 100.0;
        double total = pricePerUnit * quantity;
        string orderId = "ORD-" + to_string(orders.size() + 1);
        orders.push_back(orderId);

        // Responsibility 3: Update inventory
        it->second -= quantity;

        // Responsibility 4: Send notification
        cout << "Email to " << customerEmail << ": Order " << orderId
                  << " confirmed. Total: $" << total << endl;
    }
};

// TODO: Refactor into OrderProcessor, InventoryManager, and NotificationService.

class Order{
    string orderId;
    double totalAmount;

    public:
        Order(const string& orderId,const double& totalAmount): orderId(orderId),totalAmount(totalAmount){}

        string getId() const {
            return orderId;
        }

        double getTotal() const {
            return totalAmount;
        }
};

class InventoryManager{
    map<string, int> inventory;
    public:
        InventoryManager(){
            inventory["LAPTOP"] = 10;
            inventory["PHONE"] = 25;
            inventory["TABLET"] = 15;
        }

        bool isItemAvailable(const string& productId,const int& quantity){
            auto it = inventory.find(productId);
            if (it == inventory.end() || it->second < quantity) {
                return false;
            }
            return true;
        }

        void updateInventoryItem(const string& productId,const int& quantity){
            auto it=inventory.find(productId);
            it->second-=quantity;
        }
};

class NotificationService{
    public:
        void sendEmail(const string& customerEmail,const Order& order){
            cout << fixed << setprecision(1);
            cout << "Email to " << customerEmail << ": Order " << order.getId()
                  << " confirmed. Total: $" << order.getTotal() << endl;
        }
};

class OrderProcessor{
    InventoryManager& inventoryManager;
    NotificationService& notificationService;
    vector<Order> orders;

    void processOrder(const int& quantity){
        double pricePerUnit = 100.0;
        double total = pricePerUnit * quantity;
        string orderId = "ORD-" + to_string(orders.size() + 1);
        orders.emplace_back(orderId,total);
    }

    public:
        OrderProcessor(InventoryManager& inventoryManager,NotificationService& notificationService) : 
        inventoryManager(inventoryManager),notificationService(notificationService){}



        void placeOrder(const string& productId,const int& quantity,const string& customerEmail){
            if(!inventoryManager.isItemAvailable(productId,quantity)){
                cout << "Insufficient stock for " << productId << endl;
                return;
            }

            processOrder(quantity);

            inventoryManager.updateInventoryItem(productId,quantity);

            notificationService.sendEmail(customerEmail,orders.back());
        }
};

int main() {
    // After refactoring, usage should look like:
    InventoryManager inventory;
    NotificationService notifications;
    OrderProcessor processor(inventory, notifications);
    processor.placeOrder("LAPTOP", 2, "alice@example.com");
    return 0;
}