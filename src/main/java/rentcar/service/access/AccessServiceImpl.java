package rentcar.service.access;


import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import rentcar.model.support.User;
import rentcar.model.support.UserImage;
import rentcar.service.common.MailService;
import rentcar.service.fillintables.FillUsers;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserProfileService;
import rentcar.service.user.UserService;

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

    @Autowired
    UserImageService userImageService;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    private static String mailFrom = "kartawcew.b@gmail.com";

    @Override
    public void mailUser(String mailSubject, String link, String email, HashMap<String, Object> model) {
        String mailText = getFreeMarkerTemplateContent(model, link);
        MimeMessagePreparator mailPreparator = getMessagePreparator(mailSubject, mailText, email);
        mailService.sendEmail(mailPreparator);
        informMeAboutAnyRequest(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    private MimeMessagePreparator getMessagePreparator(final String mailSubject, final String mailText, final String email) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject(mailSubject);
                helper.setFrom(mailFrom);
                helper.setTo(email);
                helper.setText(mailText, true);
            }
        };
        return preparator;
    }

    private String getFreeMarkerTemplateContent(Map<String, Object> model, String link) {
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

    @Override
    public void createRecruiter(User user) {
        FillUsers fillUsers = new FillUsers();
        user.setLogin(fillUsers.getRandomString(8));
        user.setPassword(fillUsers.getRandomString(8));
        user.setLastName("Unknown last name");
        user.setRoles(userProfileService.getRoleSet(3));
        user.setRole();
        final User forThreadCopyUser = user;
        Thread sendMailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, Object> model = new HashMap<String, Object>();
                model.put("user", forThreadCopyUser.getFirstName());
                model.put("login", forThreadCopyUser.getLogin());
                model.put("password", forThreadCopyUser.getPassword());
                mailUser("RentCar inc. - recruiter access is granted", "mailRecruiterAccessGranted.txt", forThreadCopyUser.getEmail(), model);
            }
        });
        sendMailThread.start();
        userService.save(user);
        UserImage userImage = new UserImage();
        userImage.setId(user.getId());
        userImageService.saveUserImage(userImage);
    }

    //TODO
    @Override
    public void deleteRecruiter(User user) {
        final User forThreadCopyUser = user;
        Thread sendMailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, Object> model = new HashMap<String, Object>();
                model.put("password", forThreadCopyUser.getPassword());
                model.put("login", forThreadCopyUser.getLogin());
                mailUser("RentCar inc. - recruiter access is removed", "mailRecruiterAccessRemoved.txt", forThreadCopyUser.getEmail(), model);
            }
        });
        sendMailThread.start();
        userImageService.deleteUserImageByLogin(user.getLogin());
        userService.deleteByLogin(user.getLogin());
    }
}
