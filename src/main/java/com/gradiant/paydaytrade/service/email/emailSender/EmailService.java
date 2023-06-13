package com.gradiant.paydaytrade.service.email.emailConfirmation.emailSender;

import com.gradiant.paydaytrade.exception.CustomException;
import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.Properties;
import java.util.logging.Logger;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Service


public class EmailService  {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
// private final JavaMailSender mailSender;
//    public void sendHtmlEmail(String host, String port,
//                              final String userName, final String password, String toAddress,
//                              String subject, String message) throws AddressException,
//            MessagingException {
//
//        // sets SMTP server properties
//        Properties properties = new Properties();
//        properties.put("mail.man.com", host);
//        properties.put("mail.25", port);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.ssl.trust", "mail.man.com");
//
//        // creates a new session with an authenticator
//        Authenticator auth = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(userName, password);
//            }
//        };
//    }
//    @Override
//@Async
//
//    public void send(String to, String mail) {
//
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper =
//                    new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(mail, true);
//            helper.setTo(to);
//            helper.setSubject("Confirm your email");
//            helper.setFrom("samiroruclu@gmail.com");
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new CustomException("failed to send email");
//        }
//    }
//

}
