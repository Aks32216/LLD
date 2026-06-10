#include <iostream>

using namespace std;

// Pure virtual functions make a class abstract — it cannot be instantiated directly.
// Any derived class MUST override all pure virtual methods, or it also becomes abstract.
// Use when the base class defines a contract but has no meaningful default implementation.

class UIFramework{
    public:
        // '= 0' makes this a pure virtual function → UIFramework is now abstract
        virtual void useFramework() = 0;

        // A pure virtual destructor is valid but MUST still have a definition (see below).
        // Needed here to keep the class abstract while still allowing proper cleanup.
        virtual ~UIFramework()=0;
};

// Even though useFramework() is pure virtual, you can still provide a body.
// Derived classes can call it explicitly via UIFramework::useFramework() if needed.
void UIFramework::useFramework(){
    cout<<"use Framework"<<endl;
}

// Pure virtual destructor body is REQUIRED — the linker calls it during destruction
// of any derived object (destruction chain always reaches the base destructor).
UIFramework::~UIFramework(){
    cout<<"Base framework destructured"<<endl;
}


class Vue: public UIFramework{
    public:
        // Overriding the pure virtual method — Vue is now concrete (instantiable)
        void useFramework() override{
            cout<<"Use Vue js"<<endl;
        }

        virtual ~Vue(){
            cout<<"Deleted Vue Framework"<<endl;
        }
};


class React: public UIFramework{
    public:
        void useFramework() override{
            cout<<"Use React js"<<endl;
        }

        virtual ~React(){
            cout<<"Deleted React Framework"<<endl;
        }
};

int main(){
    // Heap allocation via base pointer — polymorphic dispatch + proper cleanup via virtual dtor
    UIFramework* ui = new React();
    ui->useFramework();  // Calls React::useFramework()

    delete ui;  // Destructor order: React → UIFramework

    ui = new Vue();
    ui->useFramework();  // Calls Vue::useFramework()

    delete ui;  // Destructor order: Vue → UIFramework

    return 0;
}
