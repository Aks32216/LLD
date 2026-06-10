/**
 * Topic   : Inheritance
 * Concept : Single-level public inheritance with a shared base class
 *
 * Notes:
 *  - 'public' inheritance: public/protected members of base remain public/protected in derived
 *  - 'protected' members are accessible inside derived classes but not outside (unlike public)
 *  - Derived class constructors must explicitly call the base constructor if it has parameters
 *  - Derived classes inherit all non-private members and can add their own methods
 */

#include <iostream>
#include <string>
#include <vector>
#include <memory>
using namespace std;

// ─── Your code below ──────────────────────────────────────────────────────────

// Base class — holds shared state and behaviour for all user types
class User{
    protected:
        // 'protected' so derived classes (Admin, Customer, Vendor) can access directly
        string username;
        string email;
    public:
        // Member initializer list — preferred over assignment inside body
        User(string username,string email): username(username), email(email){}

        void login(){
            cout<<"User loggedIn"<<endl;
        }

        void logout(){
            cout<<"User logged out"<<endl;
        }
};

// Admin IS-A User — inherits login/logout and adds privileged operations
class Admin: public User{
    public:
        // Must forward args to User's parameterized constructor explicitly
        Admin(string username,string email): User(username,email) {}

        void deleteUser(){
            // Can access 'username' directly because it's protected in User
            cout<<"deleting "<<username<<endl;
        }
};

// Customer IS-A User — no extra constructor needed (no new member variables)
class Customer: public User{
    public:
        void browseProduct(){
            cout<<"Browsing Product: "<<endl;
        }
};

// Vendor IS-A User — extends User with product management capability
class Vendor: public User{
    public:
        void addProduct(){
            cout<<"Adding Product: "<<endl;
        }
};

// ─── Driver ───────────────────────────────────────────────────────────────────

int main() {
    Admin a("amish","amish@gmail.com");
    a.deleteUser();  // Admin-specific method
    a.login();       // Inherited from User
    a.logout();      // Inherited from User
    return 0;
}
