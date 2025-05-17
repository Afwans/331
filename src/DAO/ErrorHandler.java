package DAO;

public class ErrorHandler {

    public static void showError(String context, Exception e) {
        System.out.println("❌ Error: " + context);
        // Optionally log full details:
        // e.printStackTrace(); // keep this only during debugging

        // Or write to log file later
    }

    public static void showSuccess(String message) {
        System.out.println("✅ " + message);
    }

    public static void showWarning(String message) {
        System.out.println("⚠️ " + message);
    }
}
