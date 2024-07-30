public class LinearSearch {
    public static int linearSearch(Product[] products, String productId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }
}