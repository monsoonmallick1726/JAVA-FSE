import java.util.Arrays;

class Product implements Comparable<Product> {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }

    @Override
    public int compareTo(Product other) {
        return this.productId.compareTo(other.productId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

class SearchAlgorithms {
    public static int linearSearch(Product[] products, String productId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Product[] products, String productId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId().compareTo(productId);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

public class ECommerceSearch {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Smartphone", "Electronics"),
            new Product("P003", "Headphones", "Electronics"),
            new Product("P004", "T-shirt", "Clothing"),
            new Product("P005", "Book", "Books")
        };

        // For binary search, we need to sort the array first
        Arrays.sort(products);

        String searchId = "P003";

        // Linear search
        long startTime = System.nanoTime();
        int linearResult = SearchAlgorithms.linearSearch(products, searchId);
        long linearTime = System.nanoTime() - startTime;

        // Binary search
        startTime = System.nanoTime();
        int binaryResult = SearchAlgorithms.binarySearch(products, searchId);
        long binaryTime = System.nanoTime() - startTime;

        System.out.println("Linear Search Result: " + linearResult + ", Time: " + linearTime + " ns");
        System.out.println("Binary Search Result: " + binaryResult + ", Time: " + binaryTime + " ns");
    }
}
