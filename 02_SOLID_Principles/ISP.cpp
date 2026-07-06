#include <iostream>
#include <string>

using namespace std;

// Before: Fat interface forces BasicPrinter to implement everything
// class MultiFunctionDevice {
// public:
//     virtual void print(const string& document) = 0;
//     virtual void scan(const string& document) = 0;
//     virtual void fax(const string& document, const string& number) = 0;
//     virtual void staple(const string& document) = 0;
//     virtual ~MultiFunctionDevice() = default;
// };

// class BasicPrinter : public MultiFunctionDevice {
// public:
//     void print(const string& document) override {
//         cout << "Printing: " << document << endl;
//     }

//     void scan(const string& /*document*/) override {
//         throw runtime_error("BasicPrinter cannot scan.");
//     }

//     void fax(const string& /*document*/, const string& /*number*/) override {
//         throw runtime_error("BasicPrinter cannot fax.");
//     }

//     void staple(const string& /*document*/) override {
//         throw runtime_error("BasicPrinter cannot staple.");
//     }
// };

class Printable{
    public: 
        virtual void print(const string& document)=0;

        virtual ~Printable()=default;
};

class Scanable{
    public:
        virtual void scan(const string& document)=0;

        virtual ~Scanable()=default;
};

class Faxable{
    public:
        virtual void fax(const string& document, const string& number)=0;

        virtual ~Faxable()=default;
};

class Stapleable{
    public:
        virtual void staple(const string& document) = 0;

        virtual ~Stapleable()=default;
};

class BasicPrinter:public Printable{
    public:
        void print(const string& document) override{
            cout<<"BasicPrinter -> "<<"Printing: " << document << endl;
        }
};

class OfficePrinter:public Printable,public Scanable,public Faxable{
    public:
        void print(const string& document) override{
            cout<<"OfficePrinter -> "<<"Printing: " << document << endl;
        }

        void scan(const string& document) override{
            cout<<"OfficePrinter -> "<<"Scanning: " << document << endl;
        }

        void fax(const string& document, const string& number) override{
            cout<<"OfficePrinter -> "<< "Faxing: " << document << " to " << number << endl;
        }
};

class FullDevice: public Printable,public Scanable,public Faxable,public Stapleable{
    public:
        void print(const string& document) override{
            cout<<"FullDevice -> "<<"Printing: " << document << endl;
        }

        void scan(const string& document) override{
            cout<<"FullDevice -> "<<"Scanning: " << document << endl;
        }

        void fax(const string& document, const string& number) override{
            cout<<"FullDevice -> "<<"Faxing: " << document << " to " << number << endl;
        }

        void staple(const string& document) override{
            cout<<"FullDevice -> "<<"Stapling: " << document << endl;
        }
};

int main() {
    BasicPrinter basicPrinter;
    OfficePrinter officePrinter;
    FullDevice fullDevice;


    basicPrinter.print("report.pdf");

    officePrinter.print("memo.pdf");
    officePrinter.scan("memo.pdf");
    officePrinter.fax("memo.pdf","555-1234");

    fullDevice.print("contract.pdf");
    fullDevice.scan("contract.pdf");
    fullDevice.fax("contract.pdf","555-5678");
    fullDevice.staple("contract.pdf");

    return 0;
}

// TODO: Create Printable, Scannable, Faxable, and Stapleable interfaces.
// TODO: Refactor BasicPrinter to implement only Printable.
// TODO: Create an OfficePrinter that implements Printable, Scannable, and Faxable.
// TODO: Create a FullDevice that implements all four interfaces.