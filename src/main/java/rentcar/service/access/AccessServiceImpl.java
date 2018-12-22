package rentcar.service.access;


import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;
import rentcar.model.User;
import rentcar.service.common.MailService;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service("accessService")
@Transactional
public class AccessServiceImpl implements AccessService {

    @Autowired
    MailService mailService;

    @Autowired
    Configuration freemarkerConfiguration;

    @Override
    public void mailUserAccessRequestSent(User user) {
        System.out.println("==== Confirmation mail about sending the access request has been sent to user " + user.getLogin() + " to the email "
                + user.getEmail() + ".");

        String mailText;
        String link = "fm_mailTemplate.txt";


        MimeMessagePreparator mailPreparator = getMessagePreparator();
        mailService.sendEmail(mailPreparator);

    }

    @Override
    public void mailUserAccessGranted(User user) {

    }

    @Override
    public void mailRecruiterAccessGranted() {

    }

    @Override
    public void mailRecruiterAccessRemoved() {

    }

    @Override
    public void mailUserAccessRemoved(User user) {

    }

    private MimeMessagePreparator getMessagePreparator(final Object object, final String mailSubject) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            User user = (User) object;

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject(mailSubject);
                helper.setFrom("kartawcew.b@gmail.com");
                helper.setTo(user.getEmail());

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("object", user);

                String text = getFreeMarkerTemplateContent(model);
                System.out.println("Template content : " + text);

                // use the true flag to indicate you need a multipart message
                helper.setText(text, true);
            }
        };
        return preparator;
    }


    private String getFreeMarkerTemplateContent(Map<String, Object> model, String link) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate( link), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing freeMaker template:" + e.getMessage());
        }
        return "";
    }
}
