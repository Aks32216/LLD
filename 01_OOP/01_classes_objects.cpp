/**
 * Topic   : OOP Basics
 * Concept : Classes, Objects, Constructors, Encapsulation
 *
 * Notes:
 *  - A class bundles data (fields) and behaviour (methods).
 *  - Use initializer lists in constructors for efficiency.
 *  - Keep data private; expose via public methods.
 */

#include <iostream>
#include <string>

class BankAccount {
public:
    BankAccount(std::string owner, double initialBalance)
        : owner_(std::move(owner)), balance_(initialBalance) {}

    void deposit(double amount) {
        if (amount <= 0) { std::cout << "Invalid deposit amount.\n"; return; }
        balance_ += amount;
        std::cout << "Deposited $" << amount << " → Balance: $" << balance_ << "\n";
    }

    void withdraw(double amount) {
        if (amount > balance_) { std::cout << "Insufficient funds.\n"; return; }
        balance_ -= amount;
        std::cout << "Withdrew $" << amount << " → Balance: $" << balance_ << "\n";
    }

    void printStatement() const {
        std::cout << "Account owner : " << owner_ << "\n"
                  << "Balance       : $" << balance_ << "\n";
    }

private:
    std::string owner_;
    double      balance_;
};

int main() {
    BankAccount acc("Alice", 1000.0);
    acc.printStatement();
    std::cout << "\n";
    acc.deposit(500);
    acc.withdraw(200);
    acc.withdraw(2000);
    std::cout << "\n";
    acc.printStatement();
    return 0;
}
