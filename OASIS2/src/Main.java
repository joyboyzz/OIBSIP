import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String userId = "12345";
    static String userPin = "6789";
    static double balance = 10000.00;
    static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("==== Welcome to Java ATM ====");
        login();

        while (true) {
            showMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("✅ Thank you for using Java ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    static void login() {
        System.out.print("Enter User ID: ");
        String enteredId = sc.next();
        System.out.print("Enter PIN: ");
        String enteredPin = sc.next();

        if (enteredId.equals(userId) && enteredPin.equals(userPin)) {
            System.out.println("✅ Login successful!");
        } else {
            System.out.println("❌ Invalid ID or PIN. Try again.\n");
            login();
        }
    }

    static void showMenu() {
        System.out.println("\n==== ATM Main Menu ====");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Choose an option: ");
    }

    static void showTransactionHistory() {
        System.out.println("\n📜 Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String record : transactionHistory) {
                System.out.println(record);
            }
        }
    }

    static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawn: ₹" + amount);
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
        }
    }

    static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
        } else {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("✅ ₹" + amount + " deposited successfully.");
        }
    }

    static void transfer() {
        System.out.print("Enter recipient user ID: ");
        String recipientId = sc.next();
        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory.add("Transferred ₹" + amount + " to user " + recipientId);
            System.out.println("✅ ₹" + amount + " transferred successfully to user " + recipientId);
        }
    }
}
