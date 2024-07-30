import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface Stock {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private double stockPrice;

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }

    public void setStockPrice(double price) {
        this.stockPrice = price;
        notifyObservers();
    }
}
// Observer Interface
interface Observer {
    void update(double stockPrice);
}

// Concrete Observer - Mobile App
class MobileApp implements Observer {
    private String name;

    public MobileApp(String name) {
        this.name = name;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(name + " Mobile App - Stock price updated: $" + stockPrice);
    }
}

// Concrete Observer - Web App
class WebApp implements Observer {
    private String name;

    public WebApp(String name) {
        this.name = name;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(name + " Web App - Stock price updated: $" + stockPrice);
    }
}
public class ObservePatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        // Create observers
        Observer mobileApp1 = new MobileApp("Investor A");
        Observer mobileApp2 = new MobileApp("Investor B");
        Observer webApp = new WebApp("Stock Analyst");

        // Register observers
        stockMarket.register(mobileApp1);
        stockMarket.register(mobileApp2);
        stockMarket.register(webApp);

        // Simulate stock price changes
        System.out.println("Stock price changed to $150.00");
        stockMarket.setStockPrice(150.00);

        System.out.println("\nDeregistering Investor B's Mobile App");
        stockMarket.deregister(mobileApp2);

        System.out.println("\nStock price changed to $155.50");
        stockMarket.setStockPrice(155.50);
    }
}
