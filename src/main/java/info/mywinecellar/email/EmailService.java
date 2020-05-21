/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    public void sendRegistrationEmail(String to, JavaMailSender mailSender, SpringTemplateEngine templateEngine) throws MessagingException {
        Context context = new Context();
        final String htmlContent = templateEngine.process("email/registerVerify", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Verify your email to access MyWineCellar.info");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    public void sendVerificationEmail(String to, String token, JavaMailSender mailSender ) throws MessagingException {

        //Context context = new Context();
        //final String htmlContent = templateEngine.process("email/registerVerify", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String confirmationUrl = "/register/confirm?token=" + token;

        helper.setTo(to);
        helper.setSubject("Verify your email to access MyWineCellar.info");
        //helper.setText(htmlContent);
        helper.setText("Registration success: " + "\t\t" + "http://localhost:8080" + confirmationUrl, true);

        mailSender.send(message);
    }
}
