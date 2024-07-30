class Order implements Comparable<Order> {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public int compareTo(Order other) {
        return Double.compare(this.totalPrice, other.totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
class SortingAlgorithms {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].compareTo(orders[j + 1]) > 0) {
                    // Swap orders[j] and orders[j+1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        Order pivot = orders[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].compareTo(pivot) <= 0) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        // Swap orders[i+1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }
}
public class OrderSorting {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "Alice", 150.0),
            new Order("O002", "Bob", 99.99),
            new Order("O003", "Charlie", 199.99),
            new Order("O004", "David", 75.5),
            new Order("O005", "Eve", 120.0)
        };

        // Create a copy for Quick Sort
        Order[] ordersCopy = orders.clone();

        System.out.println("Original Orders:");
        printOrders(orders);

        // Bubble Sort
        long startTime = System.nanoTime();
        SortingAlgorithms.bubbleSort(orders);
        long bubbleSortTime = System.nanoTime() - startTime;

        System.out.println("\nOrders after Bubble Sort:");
        printOrders(orders);
        System.out.println("Bubble Sort Time: " + bubbleSortTime + " ns");

        // Quick Sort
        startTime = System.nanoTime();
        SortingAlgorithms.quickSort(ordersCopy, 0, ordersCopy.length - 1);
        long quickSortTime = System.nanoTime() - startTime;

        System.out.println("\nOrders after Quick Sort:");
        printOrders(ordersCopy);
        System.out.println("Quick Sort Time: " + quickSortTime + " ns");
    }

    private static void printOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
