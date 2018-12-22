package rentcar.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import rentcar.service.user.UserServiceImpl;


public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;


    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void emailSending() {
        System.out.println("NOT WORKS");
//        System.out.println(userService.sendEmail());
//        Assert.assertEquals(userService.sendEmail(), "mail sent");
        System.out.println("WORKS");
    }
}
