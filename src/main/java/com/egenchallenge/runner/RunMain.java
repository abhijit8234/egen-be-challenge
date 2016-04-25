package com.egenchallenge.runner;

import com.egenchallenge.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhijit on 4/24/16.
 */
public class RunMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserController controller = context.getBean("userController", UserController.class);
        controller.start();
    }
}
