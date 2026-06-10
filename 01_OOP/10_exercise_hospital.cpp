/**
 * ============================================================
 *  EXERCISE 3 — Hospital Management System  [Hard]
 * ============================================================
 *
 * CONCEPTS TESTED:
 *   - All previous concepts combined
 *   - Multi-level Inheritance
 *   - Interface (pure virtual) + Virtual Functions
 *   - Runtime Polymorphism (base pointer, vtable dispatch)
 *   - Access Modifiers across inheritance levels
 *   - Constructors chaining across multiple levels
 *   - Virtual Destructor + correct cleanup order
 *
 * ─────────────────────────────────────────────────────────────
 * CLASS HIERARCHY:
 *
 *   IPrintable  (interface)
 *       └── Person  (base class)
 *               ├── Doctor  (derived from Person)
 *               │       └── Surgeon (derived from Doctor)
 *               └── Patient (derived from Person)
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM STATEMENT:
 *
 *   1. abstract class IPrintable
 *      - One pure virtual method:
 *          * printInfo() → void
 *      - Virtual destructor
 *
 *   2. class Person : public IPrintable
 *      - Protected: string name, int age
 *      - Parameterized constructor (name, age)
 *      - Virtual method:
 *          * greet() → prints "Hi, I am [name], age [age]"
 *      - printInfo() override → prints name and age
 *      - Virtual destructor → prints "Person [name] destroyed"
 *
 *   3. class Doctor : public Person
 *      - Private: string specialization, int experienceYears
 *      - Constructor (name, age, specialization, experienceYears)
 *        → chains to Person(name, age)
 *      - greet() override → prints "Hi, I am Dr. [name], [specialization]"
 *      - virtual method:
 *          * treat(string patientName) → prints "Dr.[name] is treating [patientName]"
 *      - printInfo() override → prints name, age, specialization, experience
 *      - Destructor → prints "Doctor [name] destroyed"
 *
 *   4. class Surgeon : public Doctor
 *      - Private: string surgeryType
 *      - Constructor (name, age, surgeryType, experienceYears)
 *        → chains to Doctor(name, age, "Surgery", experienceYears)
 *      - treat() override → prints "Surgeon [name] is performing [surgeryType] surgery on [patientName]"
 *      - printInfo() override → prints all Doctor info + surgeryType
 *      - Destructor → prints "Surgeon [name] destroyed"
 *
 *   5. class Patient : public Person
 *      - Private: string disease, string assignedDoctorName
 *      - Constructor (name, age, disease)
 *      - method assignDoctor(Doctor& d):
 *          * stores the doctor's name
 *          * calls d.treat(this->name)
 *      - printInfo() override → prints name, age, disease, assigned doctor
 *      - Destructor → prints "Patient [name] destroyed"
 *
 * ─────────────────────────────────────────────────────────────
 * REQUIREMENTS:
 *   - Use IPrintable* array to store both Doctor and Patient objects
 *     and call printInfo() polymorphically
 *   - greet() and treat() must dispatch correctly via virtual (vtable)
 *   - Constructor chain must reach Person for every derived class
 *   - Deleting via IPrintable* must trigger the full destructor chain
 *     (Surgeon → Doctor → Person → IPrintable)
 *
 * ─────────────────────────────────────────────────────────────
 * EXPECTED main() (write this to test your code):
 *
 *   Surgeon s("Strange", 45, "Neuro", 15);
 *   Patient p("Amish", 25, "Migraine");
 *   p.assignDoctor(s);   // "Surgeon Strange is performing Neuro surgery on Amish"
 *
 *   // Polymorphic array
 *   IPrintable* records[] = {
 *       new Surgeon("House", 50, "Cardiac", 20),
 *       new Doctor("Wilson", 45, "Oncology", 18),
 *       new Patient("John", 30, "Fever")
 *   };
 *
 *   for (auto* r : records) {
 *       r->printInfo();   // calls correct override for each type
 *       cout << "---" << endl;
 *   }
 *
 *   for (auto* r : records) delete r;  // observe full destructor chains
 * ============================================================
 */

#include <iostream>
#include <string>
using namespace std;

// ─── Write your code below ────────────────────────────────────

class IPrintable{
    public:
        virtual void printInfo()=0;
        virtual ~IPrintable(){
            cout<<"IPrintable Destroyed"<<endl;
        }
};

class Person: public IPrintable{
    protected:
        string name;
        int age;
    public:
        Person(string name,int age): name(name),age(age){}

        virtual void greet(){
            cout<<"Hi, I am ["<<name<<"], age ["<<age<<"]"<<endl;
        }

        void printInfo() override {
            cout<<name<<" "<<age<<"\n";
        }

        string getName(){
            return name;
        }

        ~Person(){
            cout<<"Person Destroyed"<<endl;
        }
};

class Doctor: public Person{
    private:
        string specialization;
        int experienceYears;
    public:
        Doctor(string name,int age,string specialization,int experienceYears): Person(name,age), specialization(specialization),experienceYears(experienceYears){}

        void greet(){
            cout<<"Hi, I am Dr. ["<<name<<"], specialization ["<<specialization<<"]"<<endl;
        }

        virtual void treat(string patientName){
            cout<<"Dr.["<<name<<"] is treating ["<<patientName<<"]"<<endl;
        }

        void printInfo() override {
            cout<<name<<" "<<age<<" "<<specialization<<" "<<experienceYears<<endl;
        }

        ~Doctor(){
            cout<<"Doctor Destroyed"<<endl;
        }
};

class Surgeon: public Doctor{
    private:
        string surgeryType;
    public:
        Surgeon(string name,int age,string surgeryType,int experienceYears): Doctor(name,age,"Surgery",experienceYears),surgeryType(surgeryType){}

        void treat(string patientName){
            cout<<"Surgeon ["<<name<<"] is performing ["<<surgeryType<<"] surgery on ["<<patientName<<"]"<<endl;
        }

        void printInfo() override {
            Doctor::printInfo();
            cout<<surgeryType<<"\n";
        }

        ~Surgeon(){
            cout<<"Surgeon Destroyed"<<endl;
        }
};

class Patient: public Person{
    private:
        string disease;
        string assignedDoctorName;
    public:
        Patient(string name,int age,string disease): Person(name,age),disease(disease){}

        void printInfo() override {
            cout<<name<<" "<<age<<" "<<disease<<" "<<assignedDoctorName<<"\n";
        }

        void assignDoctor(Doctor& d){
            assignedDoctorName=d.getName();
            d.treat(name);
        }

        ~Patient(){
            cout<<"Paitent ["<<name<<"] Destroyed"<<endl;
        }
};

