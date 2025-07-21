import javax.swing.*;
import java.util.*;

class Book {
    String id, title, author;
    boolean isIssued = false;

    Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return id + " - " + title + " by " + author + (isIssued ? " [Issued]" : "");
    }
}

public class Main {
    static ArrayList<Book> books = new ArrayList<>();
    static HashMap<String, String> issuedBooks = new HashMap<>(); // bookId -> userName

    public static void main(String[] args) {
        mainMenu();
    }

    static void mainMenu() {
        String[] options = {"Admin", "User", "Exit"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to Digital Library 📚", "Library",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> adminLogin();
            case 1 -> userMenu();
            case 2 -> System.exit(0);
        }
    }

    static void adminLogin() {
        String username = JOptionPane.showInputDialog("Enter admin username:");
        String password = JOptionPane.showInputDialog("Enter admin password:");

        if ("admin".equals(username) && "admin123".equals(password)) {
            adminMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials ❌");
            mainMenu();
        }
    }

    static void adminMenu() {
        String[] options = {"Add Book", "View Books", "Delete Book", "View Issued Books", "Back"};
        int choice = JOptionPane.showOptionDialog(null, "Admin Panel", "Admin Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> {
                String id = JOptionPane.showInputDialog("Enter Book ID:");
                String title = JOptionPane.showInputDialog("Enter Book Title:");
                String author = JOptionPane.showInputDialog("Enter Author:");
                books.add(new Book(id, title, author));
                JOptionPane.showMessageDialog(null, "Book added ✅");
                adminMenu();
            }
            case 1 -> {
                viewBooks();
                adminMenu();
            }
            case 2 -> {
                String id = JOptionPane.showInputDialog("Enter Book ID to delete:");
                books.removeIf(b -> b.id.equals(id));
                JOptionPane.showMessageDialog(null, "Book deleted ✅");
                adminMenu();
            }
            case 3 -> {
                if (issuedBooks.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No books are issued.");
                } else {
                    StringBuilder data = new StringBuilder("Issued Books:\n");
                    for (Map.Entry<String, String> e : issuedBooks.entrySet()) {
                        data.append("Book ID: ").append(e.getKey()).append(" - Issued to: ").append(e.getValue()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, data.toString());
                }
                adminMenu();
            }
            case 4 -> mainMenu();
        }
    }

    static void userMenu() {
        String userName = JOptionPane.showInputDialog("Enter your name:");
        String[] options = {"View Books", "Search Book", "Issue Book", "Return Book", "Back"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome, " + userName, "User Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> {
                viewBooks();
                userMenu();
            }
            case 1 -> {
                String keyword = JOptionPane.showInputDialog("Enter book title to search:");
                StringBuilder results = new StringBuilder("Search Results:\n");
                for (Book b : books) {
                    if (b.title.toLowerCase().contains(keyword.toLowerCase())) {
                        results.append(b).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(null, results.length() > 15 ? results.toString() : "No match found.");
                userMenu();
            }
            case 2 -> {
                String id = JOptionPane.showInputDialog("Enter Book ID to issue:");
                for (Book b : books) {
                    if (b.id.equals(id) && !b.isIssued) {
                        b.isIssued = true;
                        issuedBooks.put(id, userName);
                        JOptionPane.showMessageDialog(null, "Book issued successfully ✅");
                        userMenu();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Book not found or already issued ❌");
                userMenu();
            }
            case 3 -> {
                String id = JOptionPane.showInputDialog("Enter Book ID to return:");
                for (Book b : books) {
                    if (b.id.equals(id) && b.isIssued && userName.equals(issuedBooks.get(id))) {
                        b.isIssued = false;
                        issuedBooks.remove(id);
                        JOptionPane.showMessageDialog(null, "Book returned successfully ✅");
                        userMenu();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Invalid return ❌");
                userMenu();
            }
            case 4 -> mainMenu();
        }
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books available in the library.");
        } else {
            StringBuilder all = new StringBuilder("Books in Library:\n");
            for (Book b : books) {
                all.append(b).append("\n");
            }
            JOptionPane.showMessageDialog(null, all.toString());
        }
    }
}
