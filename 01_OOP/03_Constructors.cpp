/**
 * Topic   : Constructors
 * Concept : Default Constructors, Parameterized Constructors, Copy Constructors
 *
 * Notes:
 *  - Constructors are special methods that are called when an object is created.
 *  - Default Constructors are constructors that are called when an object is created without any parameters.
 *  - Parameterized Constructors are constructors that are called when an object is created with parameters.
 *  - Copy Constructors are constructors that are called when an object is created from another object.
 */

 #include <iostream>
 #include <string>
 #include <vector>
 #include <memory>
 
 // ─── Your code below ──────────────────────────────────────────────────────────
 
 class BankAccount {
public: 
    int balance;
    int rateOfInterest;

    BankAccount(int initialBalance, int rateOfInterest): balance(initialBalance), rateOfInterest(rateOfInterest) {}
};

class BankAccount2 {
public: 
    int balance;
    int rateOfInterest;

    BankAccount2(const BankAccount& other): balance(other.balance), rateOfInterest(other.rateOfInterest) {}
};

class BankAccount3 {
public: 
    int balance;
    int rateOfInterest;

    BankAccount3(): balance(0), rateOfInterest(0) {}
};

 // ─── Driver ───────────────────────────────────────────────────────────────────

 int main() {
    BankAccount account(100,10);
    BankAccount2 account2(account);
    BankAccount3 account3;
    std::cout << "Balance: " << account.balance << std::endl;
    std::cout << "Rate of Interest: " << account.rateOfInterest << std::endl;
    std::cout << "Balance: " << account2.balance << std::endl;
    std::cout << "Rate of Interest: " << account2.rateOfInterest << std::endl;
    std::cout << "Balance: " << account3.balance << std::endl;
    std::cout << "Rate of Interest: " << account3.rateOfInterest << std::endl;
     return 0;
 }
 