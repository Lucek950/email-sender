package com.example.emailSender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@Transactional
public class MailSenderService {
    private final int coreNumberOfMailThreads = 1;
    private final int maxNumberOfMailThreads = 1;
    private final int threadLifeLengthInSeconds = 5;
    private final MailPropertiesConfig mailPropertiesConfig;

    private final MailsCreator mailsCreator;
    private final JavaMailSenderImpl javaMailSender;
    private final ThreadPoolExecutor mailThreadPoolExecutor;

    public MailSenderService(JavaMailSenderImpl javaMailSender, MailsCreator mailsCreator, MailPropertiesConfig mailPropertiesConfig) {
        this.mailPropertiesConfig = mailPropertiesConfig;
        this.mailsCreator = mailsCreator;
        this.javaMailSender = javaMailSender;
        this.mailPropertiesConfig.setProperties(this.javaMailSender);
        this.mailThreadPoolExecutor = new ThreadPoolExecutor(
                coreNumberOfMailThreads,
                maxNumberOfMailThreads,
                threadLifeLengthInSeconds,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }

    public void sendEmailToUser() {
        User user = User.builder().name("Kuba").email("emailsenderappka@gmail.com").build();
        MimeMessage mimeMessage = mailsCreator.createEmailToUser(user, "temat", "email-template.html");
        mailThreadPoolExecutor.execute(() -> {
            try {
                javaMailSender.send(mimeMessage);
            } catch (MailException e) {
                log.error("can't send", mimeMessage, e.getMessage());
            }
        });
    }
}