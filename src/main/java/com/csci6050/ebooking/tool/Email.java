package com.csci6050.ebooking.tool;

import com.csci6050.ebooking.entity.Promotions;
import com.csci6050.ebooking.entity.User;
import net.bytebuddy.build.Plugin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


public class Email {
    public void verificationEmail(User user, String siteURL)
    {
        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "se22springb6@gmail.com";
        final String username = "se22springb6@gmail.com";//change accordingly
        final String password = "TeamB6Spring22";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Please verify your registration!");

            String content = "Dear [[name]],\n"
                    + "Please click the link below to verify your registration:\n"
                    + "\"[[URL]]\"\n"
                    + "Thank you,\n"
                    + "Cinema E-booking System TeamB6";

            content = content.replace("[[name]]", user.getFirstName());
            String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
            content = content.replace("[[URL]]", verifyURL);

            message.setText(content);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProfileNotification(User user)
    {
        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "se22springb6@gmail.com";
        final String username = "se22springb6@gmail.com";//change accordingly
        final String password = "TeamB6Spring22";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Profile has been Updated");

            String content = "Dear [[name]],\n"
                    + "You have successfully updated your profile\n"
                    + "Thank you,\n"
                    + "Cinema E-booking System TeamB6";

            content = content.replace("[[name]]", user.getFirstName());

            message.setText(content);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetPassword(User user, String siteURL)
    {
        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();
        System.out.println(to);
        // Sender's email ID needs to be mentioned
        String from = "se22springb6@gmail.com";
        final String username = "se22springb6@gmail.com";//change accordingly
        final String password = "TeamB6Spring22";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Reset your password");

            String content = "Dear [[name]],\n"
                    + "Please click the link below to change your password:\n"
                    + "\"[[URL]]\"\n"
                    + "Thank you,\n"
                    + "Cinema E-booking System TeamB6";

            content = content.replace("[[name]]", user.getFirstName());
            String verifyURL = siteURL + "/verify?code=" + user.getPwresetcode();
            content = content.replace("[[URL]]", verifyURL);

            message.setText(content);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPromotion(Iterable<User> userIterable, Promotions promo){
//        if(user.getEnrolledForPromotions() != null){
        for(User user : userIterable){
            // Recipient's email ID needs to be mentioned.
            String to = user.getEmail();
            System.out.println(to);
            // Sender's email ID needs to be mentioned
            String from = "se22springb6@gmail.com";
            final String username = "se22springb6@gmail.com";//change accordingly
            final String password = "TeamB6Spring22";//change accordingly

            // Assuming you are sending email through relay.jangosmtp.net
            String host = "smtp.gmail.com";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            // Get the Session object.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {
                // Create a default MimeMessage object.
                Message message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));

                // Set Subject: header field
                message.setSubject("New Promotions!");

                String content = "Dear [[name]],\n"
                        + "There are new promotions for our subscribers!\n"
                        + "Enter the code below at checkout to get [[Discount]] off your ticker order.\n"
                        + "\"[[CODE]]\"\n"
                        + "This offer runs from [[START]] to [[END]].\n"
                        + "Thank you,\n"
                        + "Cinema E-booking System TeamB6";

                content = content.replace("[[name]]", user.getFirstName());
                content = content.replace("[[CODE]]", promo.getPromoCode());
                content = content.replace("[[Discount]]",String.format("%.2f", promo.getPromoDiscount()));
                content = content.replace("[[START]]", promo.getPromoStart());
                content = content.replace("[[END]]", promo.getPromoEnd());

                message.setText(content);
                Transport.send(message);

                System.out.println("Sent message successfully....");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void checkoutEmail(User user, String movie, float price, List<String> seatid, long unixTime)
    {
        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();
        System.out.println(to);
        // Sender's email ID needs to be mentioned
        String from = "se22springb6@gmail.com";
        final String username = "se22springb6@gmail.com";//change accordingly
        final String password = "TeamB6Spring22";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Ticket Confirmation Email");

            String content = "Dear [[name]],\n"
                    + "You have just bought tickets to watch [[MOVIE]] on [[DATE]]\n"
                    + "The seats that have been reserved for you are as follows: [[SEAT]]\n"
                    + "The total price of your order was $[[PRICE]]\n"
                    + "Thank you for your order and we hope you enjoy your movie,\n"
                    + "Cinema E-booking System TeamB6";

            DecimalFormat df = new DecimalFormat("#.00");
            String dfPrice = df.format(price);
            String seatIDString = seatid.toString();
            Date date = new Date(unixTime);
            DateFormat ada = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//            System.out.println("Milliseconds to Date: " + df.format(date));
            String stringDate = ada.format(date);


            content = content.replace("[[name]]", user.getFirstName());
            content = content.replace("[[MOVIE]]", movie);
            content = content.replace("[[PRICE]]", dfPrice);
            content = content.replace("[[SEAT]]", seatIDString);
            content = content.replace("[[DATE]]", stringDate);

            message.setText(content);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
