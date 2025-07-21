import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();
    static Map<String, Reservation> reservations = new HashMap<>();

    static class Reservation {
        String pnr;
        String name;
        String trainNo;
        String trainName;
        String classType;
        String doj;
        String from;
        String to;

        Reservation(String pnr, String name, String trainNo, String trainName,
                    String classType, String doj, String from, String to) {
            this.pnr = pnr;
            this.name = name;
            this.trainNo = trainNo;
            this.trainName = trainName;
            this.classType = classType;
            this.doj = doj;
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) {
        users.put("admin", "admin123"); // default login

        System.out.println("==== ONLINE RESERVATION SYSTEM ====");
        System.out.print("Enter Login ID: ");
        String username = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        if (login(username, password)) {
            System.out.println("Login Successful!");

            while (true) {
                System.out.println("\n1. Reserve Ticket\n2. Cancel Ticket\n3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        reserveTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        System.out.println("Thank you for using the system.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Login Failed. Invalid credentials.");
        }
    }

    static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    static void reserveTicket() {
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.print("Enter Train Number: ");
        String trainNo = sc.next();
        String trainName = "Express-" + trainNo;
        System.out.print("Enter Class Type (e.g., Sleeper, AC): ");
        String classType = sc.next();
        System.out.print("Enter Date of Journey (dd-mm-yyyy): ");
        String doj = sc.next();
        System.out.print("From: ");
        String from = sc.next();
        System.out.print("To: ");
        String to = sc.next();

        String pnr = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Reservation res = new Reservation(pnr, name, trainNo, trainName, classType, doj, from, to);
        reservations.put(pnr, res);

        System.out.println("Reservation Successful!");
        System.out.println("Your PNR Number: " + pnr);
    }

    static void cancelTicket() {
        System.out.print("Enter PNR Number: ");
        String pnr = sc.next();

        if (reservations.containsKey(pnr)) {
            Reservation res = reservations.get(pnr);
            System.out.println("Reservation Found:");
            System.out.println("Name: " + res.name);
            System.out.println("Train: " + res.trainName + " (" + res.trainNo + ")");
            System.out.println("Journey: " + res.from + " to " + res.to + " on " + res.doj);
            System.out.print("Type 'OK' to confirm cancellation: ");
            String confirm = sc.next();

            if (confirm.equalsIgnoreCase("ok")) {
                reservations.remove(pnr);
                System.out.println("Cancellation successful.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("PNR Not Found.");
        }
    }
}
