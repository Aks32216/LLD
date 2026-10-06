package Structural_Design_Pattern.Adaptor_Design_Pattern;

interface PaymentProcessor{
    void processPayment(String currency,double amount);
    boolean isPaymentSuccessful();
    String getTransactionId();
}

class LegacyGatewayAdaptor implements PaymentProcessor{
    private final LegacyGateway legacyGateway;
    private long transactionRef;


    LegacyGatewayAdaptor(LegacyGateway legacyGateway){
        this.legacyGateway=legacyGateway;
    }

    @Override
    public void processPayment(String currency, double amount) {
        System.out.println("Adapter: Translating processPayment() for " + amount + " " + currency);
        legacyGateway.executeTransaction(amount,currency);
        transactionRef=legacyGateway.getReferenceNumber();
    }

    @Override
    public boolean isPaymentSuccessful() {
        return legacyGateway.checkStatus(transactionRef);
    }

    @Override
    public String getTransactionId() {
        return "LEGACY_TXN_" + legacyGateway.getReferenceNumber();
    }
}

class InHousePaymentProcessor implements PaymentProcessor{
    private String transactionId;
    private boolean paymentSuccessful;

    @Override
    public void processPayment(String currency, double amount) {
        System.out.println("InHouseProcessor: Processing " + amount + " " + currency);
        transactionId = "TXN_" + System.currentTimeMillis();
        paymentSuccessful = true;
        System.out.println("InHouseProcessor: Success. Txn ID: " + transactionId);
    }

    @Override
    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }
}

class LegacyGateway {
    private long transactionReference;
    private boolean paymentSuccessful;

    public void executeTransaction(double totalAmount, String currency) {
        System.out.println("LegacyGateway: Executing " + currency + " " + totalAmount);
        transactionReference = System.nanoTime();
        paymentSuccessful = true;
        System.out.println("LegacyGateway: Done. Ref: " + transactionReference);
    }

    public boolean checkStatus(long ref) {
        System.out.println("LegacyGateway: Checking status for ref: " + ref);
        return paymentSuccessful;
    }

    public long getReferenceNumber() {
        return transactionReference;
    }
}

class ChecoutService{
    private final PaymentProcessor paymentProcessor;

    ChecoutService(PaymentProcessor paymentProcessor){
        this.paymentProcessor=paymentProcessor;
    }

    public void checkout(double amount, String currency) {
        System.out.println("Checkout: Processing order for $" + amount + " " + currency);
        paymentProcessor.processPayment(currency,amount);
        if (paymentProcessor.isPaymentSuccessful()) {
            System.out.println("Checkout: Order successful! Txn: "
                    + paymentProcessor.getTransactionId());
        } else {
            System.out.println("Checkout: Order failed.");
        }
    }
}

public class Payment_Processor {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor=new InHousePaymentProcessor();
        ChecoutService checoutService=new ChecoutService(paymentProcessor);
        checoutService.checkout(512,"INR");

        PaymentProcessor paymentProcessor1=new LegacyGatewayAdaptor(new LegacyGateway());
        ChecoutService checoutService1 = new ChecoutService(paymentProcessor1);
        checoutService1.checkout(413,"USD");
    }
}
