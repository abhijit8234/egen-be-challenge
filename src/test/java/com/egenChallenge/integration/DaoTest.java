package com.egenChallenge.integration;

import com.egenchallenge.bo.Address;
import com.egenchallenge.bo.Company;
import com.egenchallenge.bo.User;
import com.egenchallenge.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by abhijit on 4/24/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class DaoTest {

    @Autowired
    private UserDao userDao;

    private static Gson gson;

    @BeforeClass
    public static void setUp() {
        gson = new GsonBuilder().create();
    }

    @Test
    public void testGetUser() {
        User user = userDao.getUser("1");
        if(user!=null) {
            System.out.println(gson.toJson(user));
        } else {
            Assert.fail();
        }

    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userDao.getAllUsers();
        for(User user: users) {
            System.out.println(gson.toJson(user));
        }
    }

    @Test
    @Rollback(false)
    public void testCreate() throws ParseException {

        User user = createNewUser();

        if (user!=null) {
            try {
                userDao.createUser(user);
                Assert.assertTrue(true);
            } catch (Exception e) {
                Assert.fail();
                System.out.printf("User Creation Failed with exception :");
                e.printStackTrace();
            }


        } else {
            Assert.fail();
        }
    }

    private User createNewUser() throws ParseException {
        User user = new User();
        user.setId("1");
        user.setFirstName("Jack");
        user.setLastName("Dawson");
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date createdDate = dateFormat.parse("24-Apr-2016");
        user.setDateCreated(createdDate);
        user.setEmail("jack@gmail.com");
        user.setProfilePic("/path/to/pic");
        Address address = new Address();
        address.setCity("Newark");
        address.setCountry("USA");
        address.setState("DE");
        address.setStreet("2");
        address.setZip(19713);

        user.setAddress(address);
        Company company = new Company();
        company.setName("ABC");
        company.setWebsite("www.abc.com");

        user.setCompany(company);

        return user;

    }

    @Test
    public void createUserJson() throws ParseException {
        System.out.println(gson.toJson(createNewUser()));
        User user = gson.fromJson("{\"id\":\"1\",\"firstName\":\"Jack\",\"lastName\":\"Dawson\",\"email\":\"jack@gmail.com\",\"address\":{\"street\":\"2\",\"city\":\"Newark\",\"zip\":19713,\"state\":\"DE\",\"country\":\"USA\"},\"dateCreated\":\"Apr 24, 2016 12:00:00 AM\",\"company\":{\"name\":\"ABC\",\"website\":\"www.abc.com\"},\"profilePic\":\"/path/to/pic\"}", User.class);
        System.out.println(user.getFirstName());

    }

}
