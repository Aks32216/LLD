package Behavioural_Design_Pattern.Strategy_Design_Pattern;

class Order{
    private double weight;
    private String zone;
    private double value;

    Order(double weight,String zone,double value){
        this.weight=weight;
        this.zone=zone;
        this.value=value;
    }

    public double getTotalWeight() {
        return weight;
    }

    public String getDestinationZone(){
        return zone;
    }

    public double getOrderValue(){
        return value;
    }
}

interface ShippingStrategy{
    double calculateCost(Order order);
}

class FlatRateShipping implements ShippingStrategy {
    private double rate;

    public FlatRateShipping(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateCost(Order order) {
        System.out.println("Calculating with Flat Rate strategy ($" + rate + ")");
        return rate;
    }
}

class WeightBasedShipping implements ShippingStrategy {
    private final double ratePerKg;

    public WeightBasedShipping(double ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public double calculateCost(Order order) {
        System.out.println("Calculating with Weight-Based strategy ($" + ratePerKg + "/kg)");
        return order.getTotalWeight() * ratePerKg;
    }
}

class DistanceBasedShipping implements ShippingStrategy {
    private double ratePerKm;

    public DistanceBasedShipping(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    @Override
    public double calculateCost(Order order) {
        System.out.println("Calculating with Distance-Based strategy for zone: " + order.getDestinationZone());
        return switch (order.getDestinationZone()) {
            case "ZoneA" -> ratePerKm * 5.0;
            case "ZoneB" -> ratePerKm * 7.0;
            default -> ratePerKm * 10.0;
        };
    }
}

class ThirdPartyApiShipping implements ShippingStrategy {
    private final double baseFee;
    private final double percentageFee;

    public ThirdPartyApiShipping(double baseFee, double percentageFee) {
        this.baseFee = baseFee;
        this.percentageFee = percentageFee;
    }

    @Override
    public double calculateCost(Order order) {
        System.out.println("Calculating with Third-Party API strategy.");
        // Simulate API call
        return baseFee + (order.getOrderValue() * percentageFee);
    }
}

class ShippingCostService {
    private ShippingStrategy strategy;

    public ShippingCostService(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ShippingStrategy strategy) {
        System.out.println("ShippingCostService: Strategy changed to " + strategy.getClass().getSimpleName());
        this.strategy = strategy;
    }

    public double calculateShippingCost(Order order) {
        if (strategy == null) {
            throw new IllegalStateException("Shipping strategy not set.");
        }
        double cost = strategy.calculateCost(order);
        System.out.println("ShippingCostService: Final Calculated Shipping Cost: $" + cost +
                " (using " + strategy.getClass().getSimpleName() + ")");
        return cost;
    }
}

public class Shipping_Calculator {
    public static void main(String[] args) {
        Order order1 = new Order(42,"ZoneA",56);

        // Create different strategy instances
        ShippingStrategy flatRate = new FlatRateShipping(10.0);
        ShippingStrategy weightBased = new WeightBasedShipping(2.5);
        ShippingStrategy distanceBased = new DistanceBasedShipping(5.0);
        ShippingStrategy thirdParty = new ThirdPartyApiShipping(7.5, 0.02);

        // Create context with an initial strategy
        ShippingCostService shippingService = new ShippingCostService(flatRate);

        System.out.println("--- Order 1: Using Flat Rate (initial) ---");
        shippingService.calculateShippingCost(order1);

        System.out.println("\n--- Order 1: Changing to Weight-Based ---");
        shippingService.setStrategy(weightBased);
        shippingService.calculateShippingCost(order1);

        System.out.println("\n--- Order 1: Changing to Distance-Based ---");
        shippingService.setStrategy(distanceBased);
        shippingService.calculateShippingCost(order1);

        System.out.println("\n--- Order 1: Changing to Third-Party API ---");
        shippingService.setStrategy(thirdParty);
        shippingService.calculateShippingCost(order1);
    }
}
