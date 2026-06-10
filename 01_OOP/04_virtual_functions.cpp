#include <iostream>
using namespace std;

// Virtual functions enable runtime polymorphism (dynamic dispatch).
// The correct overridden method is called based on the actual object type,
// not the pointer/reference type.

class Shape{
    public:
        // 'virtual' tells the compiler to resolve this via vtable at runtime
        virtual void draw(){
            cout<<"Draw the shape"<<endl;
        }

        // Virtual destructor ensures derived class destructors are called
        // when deleting via a base class pointer — prevents memory leaks
        virtual ~Shape(){
            cout<<"Shape destructured"<<endl;
        }
};

class Rectangle: public Shape{
    public:
        // 'override' is a compile-time safety net: error if base has no matching virtual
        void draw() override {
            cout<<"Draw rectange"<<endl;
        }

        // Called before Shape's destructor (destruction order: derived → base)
        ~Rectangle(){
            cout<<"Rectangle destructured"<<endl;
        }
};

class Square: public Shape{
    public:
        void draw() override {
            cout<<"Draw square"<<endl;
        }

        ~Square(){
            cout<<"Sqhare destructured"<<endl;
        }
};

int main(){
    Rectangle r;
    Shape* s = &r;  // Base pointer holds derived object
    s->draw();      // Calls Rectangle::draw() — dynamic dispatch via vtable

    Square sq;
    s=&sq;
    s->draw();      // Calls Square::draw()
}
