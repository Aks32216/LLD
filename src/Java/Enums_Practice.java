package Java;

/*
Problem Statement:
Build a Currency enum where each constant carries data (its exchange rate relative to a base currency, USD)
and the neum exposes behaviour to convert between any two currencies.

Requirement:
 - Each currency constanct stores: a display symbol and its rate to USD (how many of this currency = 1 USD)
 - A method toUSD(double amount) -> converts an amount in this currency into USD
 - A method convertTo (Currency target, double amount) -> converts amount int this currency to target currency
 - A method format (double amount) -> returns a nicely formatted string with the sumbol
*/

enum Currency{
    USD("$",1),
    INR("Ru",96),
    EURO("E",0.92),
    JPY("J",145);

    private final String symbol;
    private final double rate;

    Currency(String symbol,double rate){
        this.symbol=symbol;
        this.rate=rate;
    }

    double toUSD(double amount){
        return (amount/this.rate);
    }

    double convertTo(Currency target,double amount){
        double usdValue=toUSD(amount);
        System.out.println(usdValue);
        double toCurrency=(target.rate)* usdValue;
        return toCurrency;
    }
}

public class Enums_Practice {
    public static void main(String[] args) {
        Currency currency=Currency.USD;
        double value=currency.toUSD(150);
        System.out.println(value);

        double value2=currency.convertTo(Currency.INR,150);
        System.out.println(value2);
    }
}
