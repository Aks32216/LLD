package Creational_Design_Pattern.Factory_Design_Pattern;

/*
    An e-commerce app supports multiple payment providers (Razorpay, Stripe, PayPal). Each has
    as totally different SDK and setup. The checkout code should request a payment processor by
    name/config and use a common interface. Adding a new provicer shouldn't touch checkout.
*/

interface PaymentService{
    public void checkout(double amount);
}

class RazorPayService implements PaymentService{
    @Override
    public void checkout(double amount) {
        System.out.println("Checking out via Razorpay with amount "+amount);
    }
}

class StripePayService implements PaymentService{
    @Override
    public void checkout(double amount) {
        System.out.println("Checking out via Stripe "+amount);
    }
}

class PaymentServiceCreator{
    public static PaymentService createPaymentService(String paymentServiceName){
        return switch (paymentServiceName) {
            case "stripe" -> new StripePayService();
            case "razorpay" -> new RazorPayService();
            default -> throw new IllegalArgumentException();
        };
    }
}



public class Payment_Gateway_Integration {
    public static void main(String[] args) {
        PaymentService paymentService = PaymentServiceCreator.createPaymentService("stripe");
        paymentService.checkout(54);
        paymentService=PaymentServiceCreator.createPaymentService("razorpay");
        paymentService.checkout(65);
    }
}
