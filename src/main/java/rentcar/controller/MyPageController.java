package rentcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import rentcar.model.*;
import rentcar.service.user.UserImageService;
import rentcar.service.user.UserService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by a261711 on 2017-12-24.
 */
@Controller
@RequestMapping("/")
public class MyPageController extends AbstractController {

    @Autowired
    UserService userService;

    @Autowired
    UserImageService userImageService;

    @RequestMapping(value = {"/support/mypage/data"}, method = RequestMethod.GET)
    public String mypage(ModelMap model) {
        User user = userService.findByLogin(getActiveUser());
        model.addAttribute("user", user);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/mypage-data";
    }

    @RequestMapping(value = {"/support/mypage/data"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/mypage-data";
        }

        userService.update(user);

        model.addAttribute("usergoto", "Go to <a href=\"/support/\">Main page</a>");
        model.addAttribute("usersuccess", "You have updated your profile");
        model.addAttribute("loggedinuser", getActiveUser());
        /*return "support/success";*/
        return "support/login";
    }

    /*=============================*/

    @RequestMapping(value = {"/support/mypage"}, method = RequestMethod.GET)
    public String uploadUserImage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        User user = userService.findByLogin(getActiveUser());
        model.addAttribute("user", user);
        model.addAttribute("fileBucket", fileModel);
        model.addAttribute("loggedinuser", getActiveUser());
        return "support/mypage-image";
    }


    @RequestMapping(value = {"/support/mypage"}, method = RequestMethod.POST)
    public String uploadUserImage(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {
        User user = userService.findByLogin(getActiveUser());
        if (result.hasErrors()) {
            FileBucket fileModel = new FileBucket();
            model.addAttribute("user", user);
            model.addAttribute("fileBucket", fileModel);
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/mypage-image";
        } else {
            UserImage userImage = new UserImage();
            MultipartFile multipartFile = fileBucket.getFile();
            try {
                userImage.setId(user.getId());
                userImage.setUserImage(multipartFile.getBytes());
                userImageService.updateUserImage(userImage);
            } catch (IOException e) {
            }
            model.addAttribute("userimagegoto", "Go to <a href=" + "/support/mypage" + ">Back to profile page</a>");
            model.addAttribute("usersuccess", "Image to your profile has been updated successfully");
            model.addAttribute("loggedinuser", getActiveUser());
            return "support/success";
        }
    }
}
