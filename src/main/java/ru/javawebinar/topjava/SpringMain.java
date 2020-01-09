package ru.javawebinar.topjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.topjava.web.User.UserRestController;
import ru.javawebinar.topjava.web.restaurant.RestaurantRestController;

import java.util.Arrays;

//TODO repair
public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management

//        , "spring/spring-db.xml"
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml"
                , "spring/spring-db.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
//            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
//            RestaurantRestController restaurantRestController = appCtx.getBean(RestaurantRestController.class);
            //UserRestController userRestController = appCtx.getBean(UserRestController.class);
        }
    }
}
