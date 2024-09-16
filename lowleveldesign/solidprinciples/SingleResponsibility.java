package lowleveldesign.solidprinciples;

/*
Single Responsibility Principle (SRP):
Definition: A class should have only one reason to change, meaning it should have only one responsibility.
Explanation: Each class should focus on a single functionality, making it easier to maintain and modify without affecting unrelated functionalities.
Example: A class handling both user authentication and logging should be split into two separate classes, one for authentication and another for logging.
 */

// Wrong way
class UserService {
    public void registerUser(String username, String password) {
        // Logic to register user
    }

    public void sendEmail(String email, String message) {
        // Logic to send email
    }

    public void logUserActivity(String username, String activity) {
        // Logic to log user activity
    }
}


// Right Way
class UserRegistrationService {
    public void registerUser(String username, String password) {
        // Logic to register user
    }
}

class EmailService {
    public void sendEmail(String email, String message) {
        // Logic to send email
    }
}

class UserActivityLogger {
    public void logUserActivity(String username, String activity) {
        // Logic to log user activity
    }
}



public class SingleResponsibility {
}

