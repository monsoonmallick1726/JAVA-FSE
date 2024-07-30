// Target interface
interface PaymentProcessor {
    boolean processPayment(double amount);
}

// Adaptee classes (third-party payment gateways)
class PayPalGateway {
    public boolean makePayment(String accountId, double paymentAmount) {
        System.out.println("Processing PayPal payment of $" + paymentAmount);
        return true;
    }
}

class StripeGateway {
    public String charge(double amount) {
        System.out.println("Charging $" + amount + " via Stripe");
        return "Transaction ID: " + Math.random();
    }
}
// Adapter for PayPal
class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    private String accountId;

    public PayPalAdapter(String accountId) {
        this.payPalGateway = new PayPalGateway();
        this.accountId = accountId;
    }

    @Override
    public boolean processPayment(double amount) {
        return payPalGateway.makePayment(accountId, amount);
    }
}

// Adapter for Stripe
class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    public StripeAdapter() {
        this.stripeGateway = new StripeGateway();
    }

    @Override
    public boolean processPayment(double amount) {
        String transactionId = stripeGateway.charge(amount);
        return transactionId != null && !transactionId.isEmpty();
    }
}
public class AdapterPatternTest {
    public static void main(String[] args) {
        // Create payment processors using adapters
        PaymentProcessor payPalProcessor = new PayPalAdapter("user@example.com");
        PaymentProcessor stripeProcessor = new StripeAdapter();

        // Process payments
        processPayment(payPalProcessor, 100.00);
        processPayment(stripeProcessor, 200.00);
    }

    private static void processPayment(PaymentProcessor processor, double amount) {
        boolean result = processor.processPayment(amount);
        System.out.println("Payment of $" + amount + " processed successfully: " + result);
        System.out.println();
    }
}
