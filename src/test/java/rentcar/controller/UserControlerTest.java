package rentcar.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rentcar.model.Role;
import rentcar.model.User;
import rentcar.service.user.UserService;

public class UserControlerTest {

    @Mock
    UserService userService;

    @Mock
    MessageSource message;

    @InjectMocks
    UserController userController;

    @Spy
    List<User> users = new ArrayList<User>();

    @Spy
    ModelMap model;

    @Mock
    BindingResult result;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        users = getUsersList();
    }

    @Test
//    @WithMockUser(username = "bogdan")
//    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("authenticated")
    public void listUsersController() {
        when(userService.getAll()).thenReturn(users);
        Assert.assertEquals(userController.listUsers(model), "support/userslist");
        Assert.assertEquals(model.get("users"), users);
        verify(userService, atLeastOnce()).getAll();
        System.out.println("WORKS");
    }


    private List<User> getUsersList() {
        User user1 = new User();
        user1.setId(1);
        user1.setLogin("bodik");
        user1.setPassword("1234");
        user1.setFirstName("Bogdan");
        user1.setLastName("Kartavtsev");
        user1.setEmail("karikja@gmail.com");
        user1.setSex("M");
        HashSet<Role> roles1 = new HashSet<>();
        Role roleTemp = new Role();
        roleTemp.setId(4);
        roleTemp.setType("TEMP");
        roles1.add(roleTemp);
        user1.setRoles(roles1);
        user1.setRole();
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setLogin("bodik2");
        user2.setPassword("1234");
        user2.setFirstName("Bogdan2");
        user2.setLastName("Kartavtsev2");
        user2.setEmail("karikja@gmail.com2");
        user2.setSex("M");
        HashSet<Role> roles2 = new HashSet<>();
        Role roleAdmin = new Role();
        roleAdmin.setId(2);
        roleAdmin.setType("ADMIN");
        roles2.add(roleAdmin);
        user2.setRoles(roles2);
        user2.setRole();
        users.add(user2);

        return users;
    }

}
