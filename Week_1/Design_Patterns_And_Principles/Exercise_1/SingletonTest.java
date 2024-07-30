class Logger {
    private static Logger instance;

    private Logger() {
        // Private constructor to prevent instantiation
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
public class SingletonTest {
    public static void main(String[] args) {
        // Get the Logger instance
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Verify that both references point to the same object
        System.out.println("logger1 == logger2: " + (logger1 == logger2));

        // Use the logger
        logger1.log("This is a log message from logger1");
        logger2.log("This is a log message from logger2");

        // Verify that the hash codes are the same
        System.out.println("logger1 hash code: " + logger1.hashCode());
        System.out.println("logger2 hash code: " + logger2.hashCode());
    }
}
