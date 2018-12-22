package rentcar.dao;

import org.dbunit.dataset.IDataSet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rentcar.CommonMethods;
import rentcar.dao.user.UserDao;

import rentcar.service.user.UserImageService;


import static org.junit.Assert.assertNotNull;

public class UserDaoImplTest extends EntityDaoImplTest {

    @Autowired
    UserDao userDao;

    private UserImageService userImageService;

    private CommonMethods commonMethods;


    @Test
    public void findByLogin() {
        System.out.println("TEST SHIT");
        assertNotNull(userDao.findByLogin("admin"));
        System.out.println("TEST SHIT2");
    }


    @Override
    protected IDataSet getDataSet() throws Exception {
        return commonMethods.tablesXml();
    }

}
