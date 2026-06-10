/**
 * Topic   : Access Modifiers
 * Concept : Public, Private, Protected
 *
 * Notes:
 *  - Public: accessible from anywhere
 *  - Private: accessible only within the class
 *  - Protected: accessible within the class and derived classes
 */

 #include <iostream>
 #include <string>
 #include <vector>
 #include <memory>
 
 // ─── Your code below ──────────────────────────────────────────────────────────
 
 class BankAccount {
    private: 
    int balance;
    public: 
        int rateOfInterest;

        BankAccount(int initialBalance, int rateOfInterest): balance(initialBalance), rateOfInterest(rateOfInterest) {}

        void deposit(int amount) {
            balance += amount;
        }

        void withdraw(int amount) {
            balance -= amount;
        }

        int getBalance() {
            return balance;
        }

        int getRateOfInterest() {
            return rateOfInterest;
        }

        void setRateOfInterest(int rate) {
            rateOfInterest = rate;
        }
    };
 
 // ─── Driver ───────────────────────────────────────────────────────────────────
 
 int main() {
     BankAccount account(100,10);
     account.deposit(100);
     account.withdraw(50);
    //  std::cout << "Balance: " << account.balance << std::endl; // this will give error because balance is private
     std::cout<< "Balance: " << account.getBalance() << std::endl; // pass as public method
     std::cout<< "Rate of Interest: " <<account.rateOfInterest << std::endl; // pass as public variable
     return 0;
 }
 