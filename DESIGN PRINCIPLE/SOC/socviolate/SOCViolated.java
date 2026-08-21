package SOC.socviolate;

class UserRegistration {
    public void registerUser(String name, String email, String password) {
        // CONCERN 1: Validation
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password too short");
        }
        // CONCERN 2: Business logic (hashing password)
        String hashedPassword = Integer.toHexString(password.hashCode());
        // CONCERN 3: Database access
        String sql = "INSERT INTO users (name, email, password) VALUES ('"
                + name + "','" + email + "','" + hashedPassword + "')";
        System.out.println("Executing SQL: " + sql);
        // CONCERN 4: Sending a welcome email
        System.out.println("Connecting to SMTP server...");
        System.out.println("Sending welcome email to " + email);
        // CONCERN 5: Logging / output formatting
        System.out.println("[SUCCESS] User " + name
                + " registered at " + new java.util.Date());
    }
}

public class SOCViolated {
    public static void main(String[] args) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.registerUser(
                "Sourav",
                "sourav@gmail.com",
                "password123"
        );
    }
}
