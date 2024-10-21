package practice.interviewprep.flipkart;

import lowleveldesign.systems.snakeandladder.Game;

class GmailService {
    void sendEmail() {
        System.out.println("Gmail sent");
    }
}

// High module is tightly coupled;
class EmailService {
    GmailService gmailService;
    public EmailService(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    void sendEmail() {
        gmailService.sendEmail();
    }
}

interface EmailClient {
    void sendEmail();
}

class GmailClient implements EmailClient {
    public void sendEmail() {
        System.out.println("Gmail send");
    }
}

class EmailService2 {
    EmailClient emailClient;
    public EmailService2(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void sendEmail() {
        emailClient.sendEmail();
    }

}


public class Principles {
    public static void main(String args[]) {
        EmailService2 gEmailService2 = new EmailService2(new GmailClient());
        gEmailService2.sendEmail();
    }
}
