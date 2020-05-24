package interviewluty2020.listaprzebojow.service;

import interviewluty2020.listaprzebojow.exception.EmailServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailService
{
    private final JavaMailSender javaMailSender;

    public void send(String recipientAddress, String subject, String message)
    {
        if (Objects.isNull(recipientAddress))
        {
            throw new EmailServiceException("RECIPIENT ADDRESS STRING IS NULL");
        }

        if (Objects.isNull(subject))
        {
            throw new EmailServiceException("SUBJECT STRING IS NULL");
        }

        if (Objects.isNull(message))
        {
            throw new EmailServiceException("MESSAGE STRING IS NULL");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }


}






