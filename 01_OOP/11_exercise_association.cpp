/**
 * ============================================================
 *  EXERCISE 4 — Company Management System  [Medium]
 * ============================================================
 *
 * CONCEPTS TESTED:
 *   - Association (three forms):
 *       * Simple Association  — "uses-a"  (temporary, no ownership)
 *       * Aggregation         — "has-a"   (weak ownership; part outlives whole)
 *       * Composition         — "has-a"   (strong ownership; part dies with whole)
 *   - Object lifetime and ownership semantics
 *   - Value members vs pointer members
 *   - Const reference parameters
 *
 * ─────────────────────────────────────────────────────────────
 * CLASS RELATIONSHIPS:
 *
 *   Address   ←──(composition)────  Company      (Address is born/dies with Company)
 *   Employee  ←──(aggregation)────  Project      (Employee can outlive Project)
 *   Employee  ──(simple assoc)────► IDPrinter    (IDPrinter uses Employee, owns nothing)
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM STATEMENT:
 *
 *   1. class Address                     [used in Composition]
 *      - Private: string street, string city
 *      - Constructor: (street, city)
 *      - print() → prints "street, city"
 *      - Destructor → prints "Address [city] destroyed"
 *
 *   2. class Employee                    [used in Aggregation + Simple Association]
 *      - Private: string name, string employeeId
 *      - Constructor: (name, employeeId)
 *      - getName()   → returns name
 *      - printInfo() → prints "Employee: [name] (ID: [employeeId])"
 *      - Destructor  → prints "Employee [name] destroyed"
 *
 *   3. class IDPrinter                   [Simple Association]
 *      - Stateless utility class (no members)
 *      - static method: printBadge(const Employee& e)
 *          * prints "--- BADGE ---"
 *          * calls e.printInfo()
 *      NOTE: IDPrinter uses Employee only during the call — holds no reference,
 *            owns nothing. This is the simplest form of association.
 *
 *   4. class Project                     [Aggregation]
 *      - Private: string projectName, vector<Employee*> members
 *      - Constructor: (projectName)
 *      - addMember(Employee* e) → appends to members list
 *      - printTeam() → prints "Project: [projectName]" then calls printInfo()
 *                      on each member
 *      - Destructor  → prints "Project [projectName] destroyed"
 *                      (does NOT delete members — Project does not own them)
 *
 *   5. class Company                     [Composition]
 *      - Private: string companyName, Address address  ← value member, NOT a pointer
 *      - Constructor: (companyName, street, city)
 *        → initializes Address in the member initializer list
 *      - printInfo() → prints "Company: [companyName]" then calls address.print()
 *      - Destructor  → prints "Company [companyName] destroyed"
 *        (Address is destroyed automatically — no explicit delete needed)
 *
 * ─────────────────────────────────────────────────────────────
 * KEY DISTINCTIONS TO OBSERVE IN OUTPUT:
 *
 *   Composition  → Address destructor fires INSIDE Company's destructor.
 *                  You will never see Address outlive its Company.
 *
 *   Aggregation  → "Project Apollo destroyed" prints BEFORE or AFTER
 *                  "Employee Alice/Bob destroyed" depending on scope —
 *                  the two lifetimes are independent.
 *                  If Project deleted its members, the manual deletes below
 *                  would cause a double-free crash. It does not — so it is safe.
 *
 *   Simple Assoc → IDPrinter leaves no trace after printBadge() returns.
 *                  No destructor, no stored reference.
 *
 * ─────────────────────────────────────────────────────────────
 * REQUIREMENTS:
 *   - Company must store Address as a VALUE member (not Address*)
 *   - Project must store Employee* and must NOT delete them in its destructor
 *   - IDPrinter::printBadge must accept Employee by const reference
 *   - Use member initializer lists in ALL constructors
 *   - #include <vector> for Project's member list
 *
 * ─────────────────────────────────────────────────────────────
 * EXPECTED main() (write this to test your code):
 *
 *   // Composition
 *   Company c("Anthropic", "1 Market St", "San Francisco");
 *   c.printInfo();
 *   // Output: Company: Anthropic
 *   //         1 Market St, San Francisco
 *
 *   // Simple Association
 *   Employee e1("Amish", "E001");
 *   IDPrinter::printBadge(e1);
 *   // Output: --- BADGE ---
 *   //         Employee: Amish (ID: E001)
 *
 *   // Aggregation
 *   Employee* e2 = new Employee("Alice", "E002");
 *   Employee* e3 = new Employee("Bob",   "E003");
 *
 *   Project proj("Apollo");
 *   proj.addMember(e2);
 *   proj.addMember(e3);
 *   proj.printTeam();
 *
 *   delete e2;   // Employees are deleted independently — Project does not own them
 *   delete e3;
 *
 *   // At end of scope, observe destructor order:
 *   // "Project Apollo destroyed"  (proj)
 *   // "Employee Amish destroyed"  (e1)
 *   // "Company Anthropic destroyed" then "Address San Francisco destroyed"  (c)
 * ============================================================
 */

#include <iostream>
#include <string>
#include <vector>
using namespace std;

// ─── Write your code below ────────────────────────────────────

class Address {
    string street, city;
public:
    Address(string street, string city) : street(street), city(city) {}
    void print() const { cout << street << ", " << city << endl; }
    ~Address() { cout << "Address " << city << " destroyed" << endl; }
};

class Employee {
    string name, employeeId;
public:
    Employee(string name, string employeeId) : name(name), employeeId(employeeId) {}
    string getName() const { return name; }
    void printInfo() const { cout << "Employee: " << name << " (ID: " << employeeId << ")" << endl; }
    ~Employee() { cout << "Employee " << name << " destroyed" << endl; }
};

class IDPrinter {
public:
    static void printBadge(const Employee& e) {
        cout << "--- BADGE ---" << endl;
        e.printInfo();
    }
};

class Project {
    string projectName;
    vector<Employee*> members;
public:
    Project(string projectName) : projectName(projectName) {}
    void addMember(Employee* e) { members.push_back(e); }
    void printTeam() const {
        cout << "Project: " << projectName << endl;
        for (Employee* e : members) e->printInfo();
    }
    ~Project() { cout << "Project " << projectName << " destroyed" << endl; }
};

class Company {
    string companyName;
    Address address;
public:
    Company(string companyName, string street, string city)
        : companyName(companyName), address(street, city) {}
    void printInfo() const {
        cout << "Company: " << companyName << endl;
        address.print();
    }
    ~Company() { cout << "Company " << companyName << " destroyed" << endl; }
};

int main(){
    // Composition
    Company c("Anthropic", "1 Market St", "San Francisco");
    c.printInfo();

    cout << "---" << endl;

    // Simple Association
    Employee e1("Amish", "E001");
    IDPrinter::printBadge(e1);

    cout << "---" << endl;

    // Aggregation
    Employee* e2 = new Employee("Alice", "E002");
    Employee* e3 = new Employee("Bob",   "E003");

    Project proj("Apollo");
    proj.addMember(e2);
    proj.addMember(e3);
    proj.printTeam();

    delete e2;   // Employees outlive the project — deleted independently
    delete e3;
}
