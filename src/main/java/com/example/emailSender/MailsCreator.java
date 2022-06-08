package com.example.emailSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
@Component
public class MailsCreator {
    private final MailPropertiesConfig mailPropertiesConfig;
    private final TemplateEngine templateEngine;
    private static final String messagingExceptionMessage = "Problem with creating a mail message.";

    private MimeMessage creatorEmail(Email email){
        if(email.getTo() == null || email.getContent() == null) {
            return null;
        }
        MimeMessage mimeMessage = new MimeMessage((Session) null);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setFrom(mailPropertiesConfig.getUsername());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);

            return mimeMessage;
        } catch (MessagingException e) {
            log.error(messagingExceptionMessage, e);
            return null;
        }
    }

    public MimeMessage createEmailToUser(User user, String subject, String templatePath){
        Context context = new Context();
        context.setVariable("userName", user.getName());
        String body = templateEngine.process(templatePath, context);

        Email email = new Email(user.getEmail(), subject, body);

        return creatorEmail(email);
    }
}