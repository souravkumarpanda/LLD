package SOC.socfollow;

class UserValidator {
    public void validate(String name, String email, String password) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password too short");
        }
    }
}

class PasswordHasher {
    public String hash(String password) {
        return Integer.toHexString(password.hashCode());
    }
}

class UserRepository {
    public void save(String name, String email, String hashedPassword) {
        // In real code: PreparedStatement, not string concatenation
        System.out.println("Saving user to DB: " + name + ", " + email);
    }
}

class EmailService {
    public void sendWelcomeEmail(String email) {
        System.out.println("Sending welcome email to " + email);
    }
}

class RegistrationLogger {
    public void logSuccess(String name) {
        System.out.println(
                "[SUCCESS] User " + name +
                " registered at " + new java.util.Date()
        );
    }
}

class UserRegistrationService {
    private UserValidator validator;
    private PasswordHasher hasher;
    private UserRepository repository;
    private EmailService emailService;
    private RegistrationLogger logger;
    public UserRegistrationService(
            UserValidator validator,
            PasswordHasher hasher,
            UserRepository repository,
            EmailService emailService,
            RegistrationLogger logger) {

        this.validator = validator;
        this.hasher = hasher;
        this.repository = repository;
        this.emailService = emailService;
        this.logger = logger;
    }

    public void registerUser(String name, String email, String password) {
        validator.validate(name, email, password);
        String hashedPassword = hasher.hash(password);
        repository.save(name, email, hashedPassword);
        emailService.sendWelcomeEmail(email);
        logger.logSuccess(name);
    }
}

public class SOCFollowed {
    public static void main(String[] args) {
        // Create individual components
        UserValidator validator = new UserValidator();
        PasswordHasher hasher = new PasswordHasher();
        UserRepository repository = new UserRepository();
        EmailService emailService = new EmailService();
        RegistrationLogger logger = new RegistrationLogger();
        // Inject dependencies into UserRegistrationService
        UserRegistrationService registrationService =
                new UserRegistrationService(
                        validator,
                        hasher,
                        repository,
                        emailService,
                        logger
                );
        // Register a user
        registrationService.registerUser(
                "Sourav",
                "sourav@gmail.com",
                "password123"
        );
    }
}
