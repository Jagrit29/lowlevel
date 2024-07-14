package lowleveldesign.solidprinciples;
/*
High level module shouldn't directly depend on low level module
 */


// Wrong Way
// This is a low level module used to send email via Gmail
class GmailClient {
    void sendEmail(String text) {
        System.out.println("Sending email via gmail"+" "+text);
    }
}

// This is a high level module of EmailService but this is tightly coupled with low level one. We should take client as a input.
class EmailService1 {
    private GmailClient gmailClient;

    public EmailService1() {
        this.gmailClient = new GmailClient();
    }

    public void sendEmail(String text) {
        gmailClient.sendEmail(text);
    }

}

// Correct Way;

interface EmailClient2 {
    void sendEmail2(String text);
}

class GmailClient2 implements EmailClient2 {
    @Override
    public void sendEmail2(String text) {
        System.out.println("Sending email via gmail");
    }
}

class OutLookClient2 implements EmailClient2 {
    @Override
    public void sendEmail2(String text) {
        System.out.println("Sending email via outlook");
    }
}

// Now we have email client and various kind of classes implementing emailclient. Now we can use this in oru EmailService;

// Now this EmailSerice is not dependable on any of the one low level module;
class EmailService2 {
    private EmailClient2 emailClient2;

    public EmailService2(EmailClient2 emailClient2) {
        this.emailClient2 = emailClient2;
    }

    public void sendEmail2(String text) {
        emailClient2.sendEmail2(text);
    }
}


public class DependencyInversion {
    public static void main(String args[]) {

        // Wrong way as this will always send email via gmail only;
        EmailService1 emailService1 = new EmailService1();
        emailService1.sendEmail("hello");


        // correct way;
        // Now I created objects of emailClient which implement different client types;
        EmailClient2 gmail = new GmailClient2();
        EmailClient2 outlook = new OutLookClient2();

        // Still I can use my emailservice only for both;
        EmailService2 gmailService = new EmailService2(gmail);
        EmailService2 outlookService = new EmailService2(outlook);

        gmailService.sendEmail2("hello");
        outlookService.sendEmail2("hello");
    }
}
