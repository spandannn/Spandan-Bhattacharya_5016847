import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch {
    public static Book binarySearch(Book[] books, String title) {
        Arrays.sort(books, Comparator.comparing(Book::getTitle));
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}