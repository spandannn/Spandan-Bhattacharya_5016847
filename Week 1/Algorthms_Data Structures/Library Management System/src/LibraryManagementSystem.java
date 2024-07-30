public class LibraryManagementSystem {
    public static void main(String[] args) {
        Book[] books = {
                new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald"),
                new Book("B002", "1984", "George Orwell"),
                new Book("B003", "To Kill a Mockingbird", "Harper Lee")
        };

        // Linear Search
        Book book = LinearSearch.linearSearch(books, "1984");
        if (book != null) {
            System.out.println("Linear Search: Found Book - " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Linear Search: Book not found.");
        }

        // Binary Search
        book = BinarySearch.binarySearch(books, "1984");
        if (book != null) {
            System.out.println("Binary Search: Found Book - " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Binary Search: Book not found.");
        }
    }
}