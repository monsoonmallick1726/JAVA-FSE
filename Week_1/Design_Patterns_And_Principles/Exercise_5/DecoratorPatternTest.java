// Component Interface
interface Notifier {
    void send(String message);
}

// Concrete Component
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email notification: " + message);
    }
}
// Abstract Decorator
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

// Concrete Decorator - SMS
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS notification: " + message);
    }
}

// Concrete Decorator - Slack
class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Slack notification: " + message);
    }
}
public class DecoratorPatternTest {
    public static void main(String[] args) {
        // Create a basic email notifier
        Notifier emailNotifier = new EmailNotifier();
        System.out.println("Sending notification using Email:");
        emailNotifier.send("Hello, this is a test notification!");
        System.out.println();

        // Decorate with SMS notification
        Notifier emailAndSmsNotifier = new SMSNotifierDecorator(emailNotifier);
        System.out.println("Sending notification using Email and SMS:");
        emailAndSmsNotifier.send("Hello, this is a test notification!");
        System.out.println();

        // Decorate with Slack notification
        Notifier allChannelsNotifier = new SlackNotifierDecorator(emailAndSmsNotifier);
        System.out.println("Sending notification using Email, SMS, and Slack:");
        allChannelsNotifier.send("Hello, this is a test notification!");
    }
}
