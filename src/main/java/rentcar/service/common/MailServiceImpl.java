package rentcar.service.common;

import freemarker.template.Configuration;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(MimeMessagePreparator mailPreparator) {
        try {
            mailSender.send(mailPreparator);
            System.out.println("==== Message has been sent ====");
        } catch (MailException ex) {
            System.err.println("ERROR during mail sending: \n" + ex.getMessage());
        }
    }
}
