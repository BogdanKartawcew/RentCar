package rentcar.service.common;

import org.springframework.mail.javamail.MimeMessagePreparator;

public interface MailService {

    void sendEmail(MimeMessagePreparator mailPreparator);
}
