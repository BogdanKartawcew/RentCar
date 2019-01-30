package rentcar.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import rentcar.controller.support.abstractcontrollers.AbstractMyPageController;
import rentcar.service.common.FileBucket;
import rentcar.model.User;
import rentcar.model.UserImage;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.propertiesenums.Pages.Constants.*;

@Controller
@RequestMapping(COMMON_EMPTY)
public class MyPageController extends AbstractMyPageController {

    @RequestMapping(value = SUPPORT_MYPAGE_IMAGE, method = RequestMethod.GET)
    public String uploadUserImage(ModelMap model) {
        FileBucket fileBucket = new FileBucket();
        User user = userService.findByLogin(getActiveUserLogin());
        model.addAllAttributes(attributesUploadUserImage(user, fileBucket));
        model.addAllAttributes(attributesForSupportHeader());
        return P_MYPAGEIMAGE;
    }


    @RequestMapping(value = SUPPORT_MYPAGE_IMAGE, method = RequestMethod.POST)
    public String uploadUserImage(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) {
        User user = userService.findByLogin(getActiveUserLogin());
        if (result.hasErrors()) {
            model.addAllAttributes(attributesUploadUserImage(user, fileBucket));
            return P_MYPAGEIMAGE;
        } else {
            UserImage userImage = new UserImage();
            MultipartFile multipartFile = fileBucket.getFile();
            try {
                userImage.setId(user.getId());
                userImage.setUserImage(multipartFile.getBytes());
                userImageService.updateUserImage(userImage);
            } catch (IOException e) {
                System.out.println("Exception during file loading.");
            }
            model.addAllAttributes(attributesSuccess(new String[]{}, SUPPORT_MYPAGE_IMAGE, "success.usrimg.upd", "but.profilepage", null));
            return P_SUCCESS;
        }
    }

    @RequestMapping(value = SUPPORT_MYPAGE_DATA, method = RequestMethod.GET)
    public String updateUser(ModelMap model) {
        User user = userService.findByLogin(getActiveUserLogin());
        model.addAllAttributes(attributesUpdateUser(user));
        model.addAllAttributes(attributesForSupportHeader());
        return P_MYPAGEDATA;
    }

    @RequestMapping(value = SUPPORT_MYPAGE_DATA, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAllAttributes(attributesUpdateUser(user));
            return P_MYPAGEDATA;
        }

        userService.update(user);
        //TODO - send email to user with details
        return P_LOGIN; //log out after update
    }


}
