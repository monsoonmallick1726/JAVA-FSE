import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

class LibraryManagementSystem {
    private List<Book> books;
    private boolean isSorted; // To keep track of whether the list is sorted

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        isSorted = false;
    }

    public void addBook(Book book) {
        books.add(book);
        isSorted = false; // New book added, list might be unsorted now
    }

    public Book linearSearchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book binarySearchByTitle(String title) {
        if (!isSorted) {
            Collections.sort(books);
            isSorted = true;
        }
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books.get(mid).getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();

        // Add books
        lms.addBook(new Book(1, "To Kill a Mockingbird", "Harper Lee"));
        lms.addBook(new Book(2, "1984", "George Orwell"));
        lms.addBook(new Book(3, "Pride and Prejudice", "Jane Austen"));
        lms.addBook(new Book(4, "The Great Gatsby", "F. Scott Fitzgerald"));

        System.out.println("All Books:");
        lms.displayBooks();

        // Linear search
        long startTime = System.nanoTime();
        Book foundBook = lms.linearSearchByTitle("1984");
        long linearSearchTime = System.nanoTime() - startTime;
        System.out.println("\nBook found by linear search: " + foundBook);
        System.out.println("Linear search time: " + linearSearchTime + " ns");

        // Binary search
        startTime = System.nanoTime();
        foundBook = lms.binarySearchByTitle("1984");
        long binarySearchTime = System.nanoTime() - startTime;
        System.out.println("\nBook found by binary search: " + foundBook);
        System.out.println("Binary search time: " + binarySearchTime + " ns");
    }
}
