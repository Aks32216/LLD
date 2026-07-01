#include <iostream>

using namespace std;

// Before: Square extends Rectangle but breaks its contract
// class Rectangle {
// protected:
//     int width = 0;
//     int height = 0;

// public:
//     virtual void setWidth(int w) { width = w; }
//     virtual void setHeight(int h) { height = h; }
//     int getArea() const { return width * height; }
//     virtual ~Rectangle() = default;
// };

// class Square : public Rectangle {
// public:
//     void setWidth(int w) override { width = w; height = w; }
//     void setHeight(int h) override { width = h; height = h; }
// };

class Shape{
    public:
        virtual int getArea()=0;

        virtual ~Shape()=default;
};

class Rectangle:public Shape{
    private:
        int height;
        int width;
    public: 
        Rectangle(const int& height,const int& width):height(height),width(width){}

        int getArea() override {
            return height * width;
        }

        void setWidth(const int& width){
            this->width=width;
        }

        void setHeight(const int& height){
            this->height=height;
        }
};

class Square:public Shape{
    private:
        int side;
    public: 
        Square(const int& side):side(side){}

        int getArea() override {
            return side*side;
        }

        void setSide(const int& side){
            this->side=side;
        }
};

int main() {
    Rectangle rect(1,2);
    Square sq(4);
    cout << "Rectangle area: " << static_cast<int>(rect.getArea()) << endl;
    cout << "Square area: " << static_cast<int>(sq.getArea()) << endl;

    return 0;
}

// TODO: Refactor using a Shape interface with getArea().
// TODO: Rectangle and Square should be independent implementations of Shape.