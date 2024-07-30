import java.util.HashMap;

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

class InventoryManagementSystem {
    private HashMap<String, Product> inventory;

    public InventoryManagementSystem() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(String productId, String productName, int quantity, double price) {
        if (inventory.containsKey(productId)) {
            Product product = inventory.get(productId);
            product.setProductName(productName);
            product.setQuantity(quantity);
            product.setPrice(price);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public void displayInventory() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}

public class ProductInventoryManagementSystem {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Add products
        ims.addProduct(new Product("P001", "Laptop", 10, 999.99));
        ims.addProduct(new Product("P002", "Smartphone", 20, 499.99));

        // Display inventory
        System.out.println("Initial Inventory:");
        ims.displayInventory();

        // Update a product
        ims.updateProduct("P001", "Laptop", 15, 1099.99);

        // Delete a product
        ims.deleteProduct("P002");

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        ims.displayInventory();
    }
}
