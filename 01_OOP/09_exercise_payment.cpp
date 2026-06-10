/**
 * ============================================================
 *  EXERCISE 2 — Payment Processing System  [Medium]
 * ============================================================
 *
 * CONCEPTS TESTED:
 *   - Abstract Classes (Interface pattern)
 *   - Pure Virtual Functions
 *   - Virtual Destructors
 *   - Runtime Polymorphism via base pointer
 *   - Override keyword
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM STATEMENT:
 *
 *   Build a payment system where different payment methods share
 *   a common interface but each has its own processing logic.
 *
 *   1. abstract class IPayment   (the interface)
 *      - Pure virtual methods:
 *          * processPayment(double amount) → void
 *          * getPaymentType()              → string
 *          * validateDetails()             → bool
 *      - Virtual destructor (with a body that prints "IPayment destroyed")
 *
 *   2. class CreditCard : public IPayment
 *      - Private: string cardNumber, string cardHolder, int cvv
 *      - Constructor: (cardNumber, cardHolder, cvv)
 *      - processPayment(amount) → prints "Processing $X via Credit Card [last 4 digits]"
 *      - getPaymentType()       → returns "Credit Card"
 *      - validateDetails()      → returns true only if cardNumber has exactly 16 digits
 *      - Destructor             → prints "CreditCard destroyed"
 *
 *   3. class UPI : public IPayment
 *      - Private: string upiId
 *      - Constructor: (upiId)
 *      - processPayment(amount) → prints "Processing $X via UPI [upiId]"
 *      - getPaymentType()       → returns "UPI"
 *      - validateDetails()      → returns true only if upiId contains '@'
 *      - Destructor             → prints "UPI destroyed"
 *
 *   4. class PaymentProcessor  (concrete helper class, NOT derived from IPayment)
 *      - Private: IPayment* payment  (holds any payment method)
 *      - Constructor: (IPayment* p)
 *      - method execute(double amount):
 *          * calls validateDetails() — if false, print "Invalid payment details" and return
 *          * otherwise call processPayment(amount)
 *
 * ─────────────────────────────────────────────────────────────
 * REQUIREMENTS:
 *   - IPayment must be abstract (cannot be instantiated directly)
 *   - Always use IPayment* (base pointer) to hold payment objects
 *   - Virtual destructor ensures correct cleanup — observe the
 *     destructor call order in output (derived → base)
 *
 * ─────────────────────────────────────────────────────────────
 * EXPECTED main() OUTPUT (write this main to test your code):
 *
 *   IPayment* p1 = new CreditCard("1234567812345678", "Amish", 123);
 *   PaymentProcessor proc1(p1);
 *   proc1.execute(499.99);
 *
 *   IPayment* p2 = new UPI("amish@upi");
 *   PaymentProcessor proc2(p2);
 *   proc2.execute(199.00);
 *
 *   IPayment* p3 = new UPI("invalid-upi");
 *   PaymentProcessor proc3(p3);
 *   proc3.execute(99.00);   // should print "Invalid payment details"
 *
 *   delete p1;   // watch destructor order: CreditCard → IPayment
 *   delete p2;
 *   delete p3;
 * ============================================================
 */

#include <iostream>
#include <string>
using namespace std;

// ─── Write your code below ────────────────────────────────────

class IPayment{
    public:
        virtual void processPayment(double amount) = 0;
        virtual string getPaymentType()=0;
        virtual bool validateDetails()=0;

        virtual ~IPayment(){
            cout<<"IPayment destroyed"<<endl;
        }
};

class CreditCard: public IPayment{
    private:
        string cardNumber;
        string cardHolder;
        int cvv;
    public:
        CreditCard(string cardNumber,string cardHolder,int cvv): cardNumber(cardNumber),cardHolder(cardHolder),cvv(cvv){}

        void processPayment(double amount) override {
            cout<<"Processing $"<<amount<<" via Credit Card ["<<cardNumber.substr(cardNumber.length()-4)<<"]"<<endl;
        }

        string getPaymentType() override {
            return "Credit Card";
        }

        bool validateDetails() override {
            return cardNumber.length()==16;
        }

        ~CreditCard(){
            cout<<"CreditCard destroyed"<<endl;
        }
};

class UPI: public IPayment{
    private:
        string upiId;
    public:
        UPI(string upiId): upiId(upiId){}

        void processPayment(double amount) override {
            cout<<"Processing $"<<amount<<" via UPI ["<<upiId<<"]"<<endl;
        }

        string getPaymentType() override {
            return "UPI";
        }

        bool validateDetails() override {
            return upiId.find("@")!=string::npos;
        }

        ~UPI(){
            cout<<"UPI destroyed"<<endl;
        }
};

class PaymentProcessor{
    private:
        IPayment* payment;
    public:
        PaymentProcessor(IPayment* payment): payment(payment){}

        void execute(double amount){
            if(!payment->validateDetails()){
                cout<<"Invalid payment details"<<endl;
                return;
            }
            payment->processPayment(amount);
        }
};

int main(){
    IPayment* p1 = new CreditCard("1234567812345678", "Amish", 123);
    PaymentProcessor proc1(p1);
    proc1.execute(499.99);
    
    IPayment* p2 = new UPI("amish@upi");
    PaymentProcessor proc2(p2);
    proc2.execute(199.00);

    IPayment* p3 = new UPI("invalid-upi");
    PaymentProcessor proc3(p3);
    proc3.execute(99.00);   // should print "Invalid payment details"

    delete p1;   // watch destructor order: CreditCard → IPayment
    delete p2;
    delete p3;
}