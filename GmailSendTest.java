import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class GmailSendTest {
    public static void main(String[] args) throws Exception {
        final String username = "thopthirutech@gmail.com";
        final String password = "trnp vozj xuuf etrh";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("gollathirumalesh7755@gmail.com"));
        message.setSubject("Spring Boot Gmail SMTP test");
        message.setText("This is a test email from the Institute Management Spring Boot application.\n\nIf you received this, the Gmail SMTP setup is working correctly.");
        Transport.send(message);
        System.out.println("EMAIL_SENT_OK");
    }
}
