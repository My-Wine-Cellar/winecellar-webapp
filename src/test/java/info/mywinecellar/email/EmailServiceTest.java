package info.mywinecellar.email;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

class EmailServiceTest {
    private EmailService emailService;
    private JavaMailSender mockedSender;
    private SpringTemplateEngine springTemplateEngine;
    private MimeMessage mimeMessage;

    @BeforeEach
    public void setup(){
        emailService = new EmailService();
        mockedSender = Mockito.mock(JavaMailSender.class);
        springTemplateEngine = new SpringTemplateEngine();
        mimeMessage = new MimeMessage((Session)null);
    }

    @Test
    public void sendRegistrationEmail_success() throws MessagingException {
        String address = "test@uzh.ch";
        String message = "Verify your email to access MyWineCellar.info";

        Mockito.when(mockedSender.createMimeMessage()).thenReturn(mimeMessage);
        emailService.sendRegistrationEmail(address, mockedSender, springTemplateEngine);

        Assertions.assertEquals(message, mimeMessage.getSubject());
        Assertions.assertEquals(address, mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString());
    }

    @Test
    public void sendVerificationEmail_success() throws MessagingException {
        String token = "1L";
        String address = "test@uzh.ch";
        String message = "Verify your email to access MyWineCellar.info";

        Mockito.when(mockedSender.createMimeMessage()).thenReturn(mimeMessage);
        emailService.sendVerificationEmail(address, token, mockedSender);

        Assertions.assertEquals(message, mimeMessage.getSubject());
        Assertions.assertEquals(address, mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString());
    }
}