package lowleveldesign.solidprinciples;


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

