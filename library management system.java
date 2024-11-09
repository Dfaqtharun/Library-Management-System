import java.util.ArrayList;
import java.util.Scanner;

// Book class to represent a book
class Book {
    String title;
    String author;
    boolean isAvailable;

    // Constructor to initialize book details
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;  // All books are initially available
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

// Library class to manage books and users
class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Book> borrowedBooks = new ArrayList<>();

    // Add a book to the library
    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    // View all available books in the library
    public void viewBooks() {
        System.out.println("Books in the Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Borrow a book from the library
    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && book.isAvailable) {
                book.isAvailable = false;
                borrowedBooks.add(book);
                System.out.println("You borrowed: " + book.title);
                return;
            }
        }
        System.out.println("Sorry, the book is either not available or does not exist.");
    }

    // Return a book to the library
    public void returnBook(String title) {
        for (Book book : borrowedBooks) {
            if (book.title.equalsIgnoreCase(title)) {
                book.isAvailable = true;
                borrowedBooks.remove(book);
                System.out.println("You returned: " + book.title);
                return;
            }
        }
        System.out.println("You have not borrowed this book.");
    }

    // View borrowed books
    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have not borrowed any books.");
        } else {
            System.out.println("Your borrowed books:");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }
}

// Main class with user interface
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Adding some books to the library
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        library.addBook("1984", "George Orwell");
        library.addBook("To Kill a Mockingbird", "Harper Lee");

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. View all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. View borrowed books");
            System.out.println("5. Add a new book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // To consume the newline character

            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book you want to borrow: ");
                    String borrowTitle = sc.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 3:
                    System.out.print("Enter the title of the book you want to return: ");
                    String returnTitle = sc.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 4:
                    library.viewBorrowedBooks();
                    break;
                case 5:
                    System.out.print("Enter the title of the book: ");
                    String title = sc.nextLine();
                    System.out.print("Enter the author of the book: ");
                    String author = sc.nextLine();
                    library.addBook(title, author);
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
