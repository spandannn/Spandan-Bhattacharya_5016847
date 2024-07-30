import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch {
    public static int binarySearch(Product[] products, String productId) {
        Arrays.sort(products, Comparator.comparing(Product::getProductId));
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = products[mid].getProductId().compareTo(productId);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}