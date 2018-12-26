package rentcar.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import rentcar.service.fillintables.FillUsers;
import rentcar.model.support.User;
import rentcar.service.access.AccessServiceImpl;

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
        //Assert.assertNotNull(accessService.getFreeMarkerTemplateContent(model, link));
        System.out.println("WORKS");
    }
}
