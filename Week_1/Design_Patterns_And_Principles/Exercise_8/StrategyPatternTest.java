// Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy - Credit Card Payment
class CreditCardPayment implements PaymentStrategy {
    private String name;
    private String cardNumber;

    public CreditCardPayment(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid with credit card (Card Number: " + cardNumber + ")");
    }
}

// Concrete Strategy - PayPal Payment
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using PayPal (Email: " + email + ")");
    }
}
// Context
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Please set a payment strategy first.");
            return;
        }
        paymentStrategy.pay(amount);
    }
}
public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        // Create payment strategies
        PaymentStrategy creditCardPayment = new CreditCardPayment("John Doe", "1234-5678-9012-3456");
        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com");

        // Make a payment using credit card
        System.out.println("Paying with Credit Card:");
        paymentContext.setPaymentStrategy(creditCardPayment);
        paymentContext.executePayment(100.00);

        // Make a payment using PayPal
        System.out.println("\nPaying with PayPal:");
        paymentContext.setPaymentStrategy(payPalPayment);
        paymentContext.executePayment(50.00);

        // Attempt to make a payment without setting a strategy
        System.out.println("\nAttempting to pay without setting a strategy:");
        paymentContext.setPaymentStrategy(null);
        paymentContext.executePayment(75.00);
    }
}
