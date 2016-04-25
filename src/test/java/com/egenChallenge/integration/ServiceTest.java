package com.egenChallenge.integration;

import com.egenchallenge.bo.Address;
import com.egenchallenge.bo.Company;
import com.egenchallenge.bo.User;
import com.egenchallenge.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abhijit on 4/24/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class ServiceTest {

    private static final String USER_AGENT = "Mozilla/5.0";

    @Autowired
    private UserDao userDao;

    private static Gson gson;

    @BeforeClass
    public static void setUp() {
        gson = new GsonBuilder().create();

    }

    @Test
    // HTTP POST request
    public void sendPost() throws Exception {

        String url = "http://localhost:9900/users";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        String urlParameters = "user=" + gson.toJson(createNewUser());

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    private User createNewUser() throws ParseException {
        User user = new User();
        user.setId("2");
        user.setFirstName("Jane");
        user.setLastName("Miller");
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date createdDate = dateFormat.parse("24-Apr-2016");
        user.setDateCreated(createdDate);
        user.setEmail("jane@gmail.com");
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




}
