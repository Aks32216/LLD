#include <iostream>
#include <iomanip>
#include <string>
#include <stdexcept>

using namespace std;

class ShippingStrategy{
    public:
        virtual double calculateCost(const double& weight) const =0;
        virtual ~ShippingStrategy()=default;
};

class StandardShipping:public ShippingStrategy{
    public:
        double calculateCost(const double& weight) const override {
            return 1.5*weight;
        }
};

class ExpressShipping:public ShippingStrategy{
    public:
        double calculateCost(const double& weight) const override {
            return 3.0*weight;
        }
};

class OvernightShipping:public ShippingStrategy{
    public:
        double calculateCost(const double& weight) const override {
            return 5.0*weight;
        }
};

class InternationalShipping:public ShippingStrategy{
    public:
        double calculateCost(const double& weight) const override {
            return 10.0*weight;
        }
};


class ShippingCostCalculator {
public:
    double calculate(const ShippingStrategy& shippingStrategy, double weight) {
        return shippingStrategy.calculateCost(weight);
    }
};

int main() {

    StandardShipping std;
    ExpressShipping exp;
    OvernightShipping ovnt;
    InternationalShipping intr;

    ShippingCostCalculator calculator;
    double weight = 2.0;

    cout << fixed << setprecision(1);
    cout << "Standard: $"     << calculator.calculate(std,  weight) << endl;
    cout << "Express: $"      << calculator.calculate(exp,  weight) << endl;
    cout << "Overnight: $"    << calculator.calculate(ovnt, weight) << endl;
    cout << "International: $"<< calculator.calculate(intr, weight) << endl;

    return 0;
}

// TODO: Define a ShippingStrategy interface with a calculateCost(weight) method.
// TODO: Create concrete implementations for each shipping type.
// TODO: Refactor ShippingCostCalculator to accept a ShippingStrategy.