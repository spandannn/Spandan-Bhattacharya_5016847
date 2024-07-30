public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory i = new Inventory();

        // Add products
        Product product1 = new Product("P-001", "Laptop", 10, 79999.99);
        Product product2 = new Product("P-002", "Smartphone", 20, 54499.99);
        i.addProduct(product1);
        i.addProduct(product2);

        // Update product
        product1.setPrice(89999.99);
        i.updateProduct(product1);

        // Get and display product
        Product retrievedProduct = i.getProduct("P-001");
        System.out.println("Product ID: " + retrievedProduct.getProductId());
        System.out.println("Product Name: " + retrievedProduct.getProductName());
        System.out.println("Quantity: " + retrievedProduct.getQuantity());
        System.out.println("Price: " + retrievedProduct.getPrice());


        // Delete product
        i.deleteProduct("P002");
    }
}