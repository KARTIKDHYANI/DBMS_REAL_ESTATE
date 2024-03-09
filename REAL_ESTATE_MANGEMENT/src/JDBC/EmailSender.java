package JDBC;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    // Your email credentials
    private String username;
    private String password;

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String recipientEmail, String subject, String messageBody) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // or your mail server host
        props.put("mail.smtp.port", "587"); // check your mail server's port
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // or TLSv1.3
        props.put("mail.debug", "true");
            

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email. Error: " + e.getMessage(), e);
        }
    }

    
}
