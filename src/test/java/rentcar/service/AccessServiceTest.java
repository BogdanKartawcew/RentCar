package rentcar.service;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import rentcar.fillintables.FillUsers;
import rentcar.model.User;
import rentcar.service.access.AccessServiceImpl;
import rentcar.service.user.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class AccessServiceTest {
    @InjectMocks
    AccessServiceImpl accessService;


    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void emailSending() {
        System.out.println("NOT WORKS");
        FillUsers fillUsers = new FillUsers();
        User user = fillUsers.createTestUser();
        System.out.println(user);

        String link = "accessRequestSent.txt";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("object", user);
        Assert.assertNotNull(accessService.getFreeMarkerTemplateContent(model, link));
        System.out.println("WORKS");
    }
}
