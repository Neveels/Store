package com.example.userservice.utils;

import com.example.userservice.exception.type.BusinessException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailSender {

    private final JavaMailSender mailSender;
    @Value("${mail.username}")
    private String username;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String emailTo, String password) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailTo);
            helper.setSubject("Ваш временный пароль для доступа в систему Apple");
            helper.setFrom(username, "Apple store");
            helper.setText(getHtml(password), true);
            mailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new BusinessException("Сообщение не отправлено", HttpStatus.NOT_IMPLEMENTED);
        }
    }


    private String getHtml(String password) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title>Отправка временного пароля для нового работника</title>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<p>Здравствуйте,</p>\n" +
                "\t<p>Ваш временный пароль для доступа в систему Apple :</p>\n" +
                "\t<h3>" + password + "</h3>\n" +
                "\t<p>С уважением,</p>\n" +
                "\t<p>Команда Apple </p>\n" +
                "</body>\n" +
                "</html>";
    }

}