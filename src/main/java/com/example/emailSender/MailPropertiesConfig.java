package com.example.emailSender;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Getter
public class MailPropertiesConfig {

    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String start;//
    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String tlsRequired;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;//
    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout;
    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private int timeout;
    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private int writeTimeout;
    @Value("${spring.mail.debug}")
    private String debug;//
    @Value("${spring.mail.protocol}")
    private String transportProtocol;//
    @Value("${spring.mail.default-encoding}")
    private String defaultEncoding;
    @Value("${spring.mail.test-connection}")
    private String testConnection;

    public void setProperties(JavaMailSenderImpl javaMailSender) {
        javaMailSender.setHost(getHost());
        javaMailSender.setPort(getPort());
        javaMailSender.setUsername(getUsername());
        javaMailSender.setPassword(getPassword());

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", getTransportProtocol());
        properties.put("mail.smtp.auth", getAuth());
        properties.put("mail.smtp.starttls.enable", getStart());
        properties.put("mail.debug", getDebug());
        properties.put("mail.smtp.starttls.required", getTlsRequired());
        properties.put("mail.smtp.connectiontimeout", getConnectionTimeout());
        properties.put("mail.smtp.timeout", getTimeout());
        properties.put("mail.smtp.writetimeout", getWriteTimeout());
        properties.put("mail.default-encoding", getDefaultEncoding());
        properties.put("mail.test-connection", getTestConnection());
    }
}