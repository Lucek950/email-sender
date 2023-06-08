# Email Sending Service
## Description
The project is an email sending service that is connected to a Gmail server. The service allows sending emails to a hardcoded user. The email content is included in HTML templates and is populated using Thymeleaf. The application is developed using Spring and Java.

## Requirements
To run this project, the following dependencies are required:

* Java 8 or higher
* Spring Framework
* Thymeleaf templates
* Gmail server

## Installation Guide
1. Clone the project repository:

```shell
git clone https://github.com/Lucek950/email-sender.git
```

2. Open the project in your favorite code editor.
3. Configure the Gmail server access credentials in the application.properties file. Set the appropriate values for the following properties:

```shell
spring.mail.port=587
spring.mail.host=smtp.gmail.com
spring.mail.username=emailsenderappka@gmail.com
spring.mail.password=cgmhpqaijdzdmmon
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.connectiontimeout=25000
spring.mail.properties.mail.smtp.timeout=25000
spring.mail.properties.mail.smtp.writetimeout=25000
spring.mail.debug=false
spring.mail.protocol=smtp
spring.mail.default-encoding=UTF-8
spring.mail.test-connection=false
```

4. Create HTML templates for the email messages in the templates folder. You can utilize Thymeleaf for dynamic data population in the templates.

5. Run the application using the following command:

```shell
./mvnw spring-boot:run
```

6. Once the application is running, you can send emails by using the API interface. Make an appropriate HTTP request to submit the message data to the service.

## Summary
The email sending service has been integrated with the Gmail server, enabling the sending of emails to a hardcoded user. The email content is generated using HTML templates and Thymeleaf. The application is developed using the Spring framework and Java.
