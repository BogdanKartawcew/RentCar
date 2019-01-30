package rentcar.service.access;


import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import rentcar.model.User;
import rentcar.service.common.MailService;
import rentcar.service.fillintables.FillUsers;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserProfileService;
import rentcar.service.user.UserService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;

@Service("accessService")
@Transactional
public class AccessServiceImpl implements AccessService {

    @Autowired
    MailService mailService;

    @Autowired
    Configuration freemarkerConfiguration;

    @Autowired
    UserImageService userImageService;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    protected MessageSource messageSource;

    private static String mailFrom = "kartawcew.b@gmail.com";

    @Override
    public void mailUser(String mailSubject, String link, String email, HashMap<String, Object> model) {
        String mailText = getFreeMarkerTemplateContent(model, link);
        MimeMessagePreparator mailPreparator = getMessagePreparator(mailSubject, mailText, email);
        mailService.sendEmail(mailPreparator);
        /*informMeAboutAnyRequest(new Object() {
        }.getClass().getEnclosingMethod().getName());*/
    }

    private MimeMessagePreparator getMessagePreparator(final String mailSubject, final String mailText, final String email) {

        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(mailSubject);
            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setText(mailText, true);
        };
    }

    private String getFreeMarkerTemplateContent(Map<String, Object> model, String link) {
        StringBuilder content = new StringBuilder();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(link), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing freeMaker template:" + e.getMessage());
            return "Message not full. Please repeat the access request. Sorry for inconvenience.";
        }
    }

   /* private void informMeAboutAnyRequest(String methodName) {

        MimeMessagePreparator mailPreparator = getMessagePreparator("RentCar inc. - Boss, new request there, please check",
                "The request is from method " + methodName, "kartawcew.b@gmail.com");
        mailService.sendEmail(mailPreparator);
    }*/

    @Override
    public void createRecruiter(User user) {
        FillUsers randomDataClass = new FillUsers();
        user.setLogin(randomDataClass.getRandomString(8));
        user.setPassword(randomDataClass.getRandomString(8));
        user.setLastName("Unknown last name");
        user.setRoles(userProfileService.getRoleSetById(3));
        sendMailThread(user, "RentCar inc. - recruiter access is granted", "mailRecruiterAccessGranted.txt");
        userService.save(user);
    }

    //TODO
    @Override
    public void deleteRecruiter(User user) {
        sendMailThread(user, "RentCar inc. - recruiter access is removed", "mailRecruiterAccessRemoved.txt");
        userImageService.deleteUserImageByLogin(user.getLogin());
        userService.deleteByLogin(user.getLogin());
    }

    @Override
    public void sendMailThread(User user, String mailTopic, String fileLink) {
        Thread sendMailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String mailSubject = messageSource.getMessage(mailTopic, new String[]{}, Locale.getDefault());
                HashMap<String, Object> model = new HashMap<String, Object>();
                model.put(LOW_USER, user.getFirstName());
                model.put(LOW_LOGIN, user.getLogin());
                model.put(LOW_PASSWORD, user.getPassword());
                mailUser(mailSubject, fileLink, user.getEmail(), model);
            }
        });
        sendMailThread.start();
    }
}
