import java.util.ArrayList;

// Abstract class - demonstrates Abstraction
abstract class LibraryItem {

    private int id;
    private String title;

    // Constructor
    LibraryItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // Encapsulation using getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // Abstract method
    abstract void displayInfo();
}


// Book inherits LibraryItem
class Book extends LibraryItem {

    private String author;
    private boolean available;

    // Constructor
    Book(int id, String title, String author) {
        super(id, title);
        this.author = author;
        this.available = true;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Overriding abstract method
    @Override
    void displayInfo() {
        System.out.println(
            "ID: " + getId() +
            ", Title: " + getTitle() +
            ", Author: " + author +
            ", Available: " + available
        );
    }
}


// User class
class User {

    private int userId;
    private String name;

    User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}


// StudentUser inherits User
class StudentUser extends User {

    StudentUser(int userId, String name) {
        super(userId, name);
    }
}


// Library class
class Library {

    private ArrayList<Book> books = new ArrayList<>();

    // Add Book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Borrow Book
    public void borrowBook(int bookId, User user) {

        for (Book book : books) {

            if (book.getId() == bookId) {

                if (book.isAvailable()) {

                    book.setAvailable(false);

                    System.out.println(
                        user.getName() +
                        " borrowed \"" +
                        book.getTitle() +
                        "\""
                    );

                } else {

                    System.out.println("Book is already borrowed.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Return Book
    public void returnBook(int bookId) {

        for (Book book : books) {

            if (book.getId() == bookId) {

                if (!book.isAvailable()) {

                    book.setAvailable(true);

                    System.out.println(
                        "\"" + book.getTitle() +
                        "\" returned successfully."
                    );

                } else {

                    System.out.println("Book was not borrowed.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Display all books
    public void displayBooks() {

        System.out.println("\n--- Library Books ---");

        for (Book book : books) {
            book.displayInfo();
        }
    }
}


// Main class
public class LibraryManagementSystem {

    public static void main(String[] args) {

        // Create Library object
        Library library = new Library();

        // Create Books
        Book book1 = new Book(
            101,
            "Java Programming",
            "James Gosling"
        );

        Book book2 = new Book(
            102,
            "Clean Code",
            "Robert Martin"
        );

        // Add books
        library.addBook(book1);
        library.addBook(book2);

        // Create User
        StudentUser user = new StudentUser(
            1,
            "Yuvashri"
        );

        // Display books
        library.displayBooks();

        // Borrow book
        System.out.println();
        library.borrowBook(101, user);

        // Display books again
        library.displayBooks();

        // Return book
        System.out.println();
        library.returnBook(101);

        // Display books again
        library.displayBooks();
    }
}