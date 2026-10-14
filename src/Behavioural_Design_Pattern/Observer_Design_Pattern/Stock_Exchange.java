package Behavioural_Design_Pattern.Observer_Design_Pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface StockExchangeSubject{
    void registerObserver(StockObserver o);
    void removeObserver(StockObserver o);
    void notifyObservers();
}

class StockExchange implements StockExchangeSubject{
    private final List<StockObserver> observerList=new ArrayList<>();
    private final Map<String,Double> prices = new HashMap<>();
    private String lastUpdatedSymbol;

    void updatePrice(String symbol, Double price){
        prices.put(symbol, price);
        lastUpdatedSymbol = symbol;
        System.out.println("\nExchange: " + symbol + " updated to $" + price);
        notifyObservers();
    }

    Double getPrice(String symbol){
        return prices.getOrDefault(symbol,0.0);
    }

    String getLastUpdatedSymbol(){
        return lastUpdatedSymbol;
    }

    @Override
    public void registerObserver(StockObserver o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(StockObserver o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(StockObserver s:observerList){
            s.onPriceUpdate(this);
        }
    }
}

interface StockObserver{
    void onPriceUpdate(StockExchange exchange);
}

class PriceDisplay implements  StockObserver{
    @Override
    public void onPriceUpdate(StockExchange exchange) {
        String symbol = exchange.getLastUpdatedSymbol();
        System.out.println("Display -> " + symbol + ": $" + exchange.getPrice(symbol));
    }
}

class AlertService implements StockObserver {
    private final Map<String, Double> thresholds = new HashMap<>();

    public void setAlert(String symbol, double threshold) {
        thresholds.put(symbol, threshold);
    }

    @Override
    public void onPriceUpdate(StockExchange exchange) {
        String symbol = exchange.getLastUpdatedSymbol();
        if (thresholds.containsKey(symbol)) {
            double threshold = thresholds.get(symbol);
            double price = exchange.getPrice(symbol);
            if (price >= threshold) {
                System.out.println("ALERT -> " + symbol + " hit $" + price +
                        " (threshold: $" + threshold + ")");
            }
        }
    }
}

class TradingBot implements StockObserver {
    private final Map<String, Double> previousPrices = new HashMap<>();

    @Override
    public void onPriceUpdate(StockExchange exchange) {
        String symbol = exchange.getLastUpdatedSymbol();
        double currentPrice = exchange.getPrice(symbol);
        double previousPrice = previousPrices.getOrDefault(symbol, currentPrice);

        if (currentPrice > previousPrice) {
            System.out.println("Bot -> " + symbol + " rising ($" + previousPrice +
                    " -> $" + currentPrice + "). HOLD.");
        } else if (currentPrice < previousPrice) {
            System.out.println("Bot -> " + symbol + " dropping ($" + previousPrice +
                    " -> $" + currentPrice + "). BUY.");
        }

        previousPrices.put(symbol, currentPrice);
    }
}

public class Stock_Exchange {
    public static void main(String[] args) {
        StockExchange exchange = new StockExchange();

        PriceDisplay display = new PriceDisplay();
        AlertService alerts = new AlertService();
        TradingBot bot = new TradingBot();

        exchange.registerObserver(display);
        exchange.registerObserver(alerts);
        exchange.registerObserver(bot);

        alerts.setAlert("AAPL", 180.0);
        alerts.setAlert("GOOG", 140.0);

        exchange.updatePrice("AAPL", 175.50);
        exchange.updatePrice("GOOG", 138.25);
        exchange.updatePrice("AAPL", 182.00);
        exchange.updatePrice("GOOG", 141.75);
    }
}
