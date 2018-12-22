package rentcar.service.access;


import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
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

        String mailSubject = "RentCar inc. - access request is under confirmation";
        String link = "accessRequestSent.txt";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user.getFirstName());
        model.put("login", user.getLogin());
        model.put("password", user.getPassword());
        String mailText = getFreeMarkerTemplateContent(model, link);
        String email = user.getEmail();
        MimeMessagePreparator mailPreparator = getMessagePreparator(mailSubject, mailText, email);
        mailService.sendEmail(mailPreparator);

        System.out.println("==== Confirmation mail about sending the access request has been sent to user " + user.getLogin() + " to the email "
                + email + ".");
        informMeAboutAnyRequest(new Object() {
        }.getClass().getEnclosingMethod().getName());
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

    private MimeMessagePreparator getMessagePreparator(final String mailSubject, final String mailText, final String email) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject(mailSubject);
                helper.setFrom("kartawcew.b@gmail.com");
                helper.setTo(email);
                helper.setText(mailText, true);
                System.out.println("Template content : " + mailText);

            }
        };
        return preparator;
    }


    public String getFreeMarkerTemplateContent(Map<String, Object> model, String link) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(link), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing freeMaker template:" + e.getMessage());
            return "Message not full. Please repeat the access request. Sorry for inconvenience.";
        }
    }

    private void informMeAboutAnyRequest(String methodName) {

        MimeMessagePreparator mailPreparator = getMessagePreparator("RentCar inc. - Boss, new request there, please check",
                "The request is from method " + methodName, "kartawcew.b@gmail.com");
        mailService.sendEmail(mailPreparator);
    }
}
