public class ECommerceSearchTest {
    public static void main(String[] args) {
        Product[] products = {
                new Product("P001", "Laptop", "Electronics"),
                new Product("P002", "Smartphone", "Electronics"),
                new Product("P003", "Tablet", "Electronics")
        };

        // Linear Search
        int index = LinearSearch.linearSearch(products, "P002");
        System.out.println("Linear Search: Product found at index " + index);

        // Binary Search
        index = BinarySearch.binarySearch(products, "P002");
        System.out.println("Binary Search: Product found at index " + index);
    }
}
