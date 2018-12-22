package rentcar.fillintables;

import rentcar.dao.user.UserDao;
import rentcar.dao.user.UserImageDao;
import rentcar.model.Role;
import rentcar.model.User;
import rentcar.model.UserImage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class FillUsers {

    private UserDao userDao;
    private UserImageDao userImageDao;


    private static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static HashMap<Integer, String> roles = new HashMap<>();
    private static int usersCount = 10;

    static {
        roles.put(1, "USER");
        roles.put(2, "ADMIN");
        roles.put(3, "SUPERUSER");
        roles.put(4, "TEMP");
    }

    public void saveUserInDb() {
        System.out.println("shitty");
        long start = System.nanoTime();
        for (int i = 0; i < usersCount; i++) {
            try {
                System.out.println("creating user in real DB");
                User user = createTestUser();
                System.out.println(user);
                userDao.save(user);
                UserImage userImage = new UserImage();
                userImage.setId(user.getId());
                userImageDao.save(userImage);
                System.out.println("User " + user + " saved to DB");
            } catch (Exception io) {
                System.out.println("some error");

            }
        }
        long elapsedTime = System.nanoTime() - start;
        System.out.println("time::::::::::::" + elapsedTime);
    }


    private User createTestUser() {
        User user = new User();
        user.setId(null);
        user.setLogin(getRandomString(6));
        user.setPassword(getRandomString(8));
        user.setFirstName(getRandomString(4));
        user.setLastName(getRandomString(9));
        user.setEmail(getRandomString(5) + "@gmail.com");
        user.setSex(getRandomSex());
        user.setRoles(getRoleSet());
        user.setRole();
        return user;
    }

    private String getRandomString(int stringLenght) {
        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < stringLenght; i++) {
            int number = getRandomNumber(alphabet.length());
            char ch = alphabet.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    private int getRandomNumber(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    private String getRandomSex() {
        Random random = new Random();
        boolean sex = random.nextBoolean();
        if (sex)
            return "F";
        else return "M";
    }


    private HashSet<Role> getRoleSet() {
        HashSet<Role> resultSet = new HashSet<>();
        int roleId = 1;
        int temp = getRandomNumber(5);
        if (temp != 0) roleId = temp;
        Role role = new Role();
        role.setId(roleId);
        role.setType(roles.get(roleId));
        resultSet.add(role);
        //resultSet.add(userProfileService.findById(roleId));
        //System.out.println(resultSet);
        return resultSet;
    }
}
