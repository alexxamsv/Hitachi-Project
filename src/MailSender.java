import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class MailSender {
    Properties properties = new Properties();
    //sendmail receive email, pass, filename
    public void sendMail(String receiverEmail, String senderEmail, String senderPassword, String fileName) throws MessagingException, IOException {
        //set properties
        properties.put("mail.smtp.host", "smtp.abv.bg");
        properties.put("mail.smtp.port", "465");
        //set authentication
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        //create new session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
             protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        //create new message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File(fileName));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        try {
            //send message
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
