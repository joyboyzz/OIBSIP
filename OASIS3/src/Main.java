import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Main {
    static String userId = "student";
    static String password = "pass123";
    static String name = "John Doe";
    static int score = 0;

    public static void main(String[] args) {
        login();
    }

    static void login() {
        String enteredId = JOptionPane.showInputDialog(null, "Enter User ID:");
        String enteredPin = JOptionPane.showInputDialog(null, "Enter Password:");

        if (enteredId != null && enteredId.equals(userId) && enteredPin != null && enteredPin.equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful! Welcome " + name + " 🎉");
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials ❌");
            login(); // retry
        }
    }

    static void showMainMenu() {
        String[] options = {"Update Profile", "Start Exam", "Logout"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose an option:",
                "Online Exam Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                updateProfile();
                break;
            case 1:
                startExam();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Logged out successfully. Goodbye! 👋");
                System.exit(0);
                break;
            default:
                break;
        }
    }

    static void updateProfile() {
        String newName = JOptionPane.showInputDialog(null, "Enter new name:");
        if (newName != null && !newName.trim().isEmpty()) {
            name = newName.trim();
        }
        String newPass = JOptionPane.showInputDialog(null, "Enter new password:");
        if (newPass != null && !newPass.trim().isEmpty()) {
            password = newPass.trim();
        }
        JOptionPane.showMessageDialog(null, "Profile updated successfully ✅");
        showMainMenu();
    }

    static void startExam() {
        score = 0;
        long start = System.currentTimeMillis();

        askQuestion("1️⃣ What is the capital of India?",
                new String[]{"Delhi", "Mumbai", "Kolkata", "Chennai"}, 0, start);

        askQuestion("2️⃣ Java is a ___ language.",
                new String[]{"Machine", "Assembly", "High-level", "Low-level"}, 2, start);

        askQuestion("3️⃣ Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Venus", "Jupiter"}, 1, start);

        JOptionPane.showMessageDialog(null, "⏳ Exam submitted!\n🎯 Your Score: " + score + "/3");
        showMainMenu();
    }

    static void askQuestion(String question, String[] options, int correctIndex, long startTime) {
        long now = System.currentTimeMillis();
        if ((now - startTime) / 1000 > 60) {
            JOptionPane.showMessageDialog(null, "⏰ Time's up! Auto-submitting exam.");
            showMainMenu();
            System.exit(0);
        }

        int answer = JOptionPane.showOptionDialog(null, question,
                "Question", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (answer == correctIndex) {
            score++;
        }
    }
}
