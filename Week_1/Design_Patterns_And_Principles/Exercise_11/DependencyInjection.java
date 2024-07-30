// Repository Interface
interface CustomerRepository {
    String findCustomerById(String id);
}

// Concrete Repository
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        // This is a mock implementation. In a real scenario, this would interact with a database.
        return "Customer " + id + ": John Doe";
    }
}
// Service Class
class CustomerService {
    private final CustomerRepository repository;

    // Constructor Injection
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String getCustomerName(String id) {
        return repository.findCustomerById(id);
    }
}
public class DependencyInjection {
    public static void main(String[] args) {
        // Create the repository
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Create the service with injected repository
        CustomerService service = new CustomerService(repository);

        // Use the service
        String customerId = "12345";
        String customerName = service.getCustomerName(customerId);
        System.out.println("Found customer: " + customerName);

        // Demonstrate flexibility of Dependency Injection
        // We can easily switch to a different implementation of CustomerRepository
        CustomerRepository mockRepository = new CustomerRepository() {
            @Override
            public String findCustomerById(String id) {
                return "Mock Customer " + id + ": Jane Smith";
            }
        };

        CustomerService mockService = new CustomerService(mockRepository);
        String mockCustomerName = mockService.getCustomerName(customerId);
        System.out.println("Found customer with mock repository: " + mockCustomerName);
    }
}
