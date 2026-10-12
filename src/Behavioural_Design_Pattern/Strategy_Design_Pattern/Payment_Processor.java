package Behavioural_Design_Pattern.Strategy_Design_Pattern;

interface PaymentStrategy{
    boolean pay(double amount);
}

class CreditCardPayment implements PaymentStrategy{
    private final String cardNo;
    private final String expDate;

    CreditCardPayment(String cardNo,String expDate){
        this.cardNo=cardNo;
        this.expDate=expDate;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Charging $" + amount + " to credit card ending in "
                + cardNo.substring(cardNo.length() - 4));
        return true;
    }
}

class PayPalPayment implements PaymentStrategy{
    private final String email;

    PayPalPayment(String email){
        this.email=email;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Sending $" + amount + " via PayPal to " + email);
        return true;
    }
}

class CryptoPayment implements PaymentStrategy{
    private final String walletAddress;

    CryptoPayment(String walletAddress){
        this.walletAddress=walletAddress;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Transferring $" + amount + " in crypto to " + walletAddress);
        return true;
    }
}

class CheckoutService{
    private PaymentStrategy paymentStrategy;

    CheckoutService(PaymentStrategy paymentStrategy){
        this.paymentStrategy=paymentStrategy;
    }

    void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy=paymentStrategy;
    }

    boolean checkout(double amount){
        return paymentStrategy.pay(amount);
    }
}

public class Payment_Processor {
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService(
                new CreditCardPayment("4111111111111111", "12/26"));
        checkout.checkout(99.99);

        checkout.setPaymentStrategy(new PayPalPayment("user@example.com"));
        checkout.checkout(49.99);

        checkout.setPaymentStrategy(new CryptoPayment("0xABC123..."));
        checkout.checkout(149.99);
    }
}
