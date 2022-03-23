package com.csci6050.ebooking;

import com.csci6050.ebooking.DB.DBConnection;
import com.csci6050.ebooking.DB.userDataAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.csci6050.ebooking.repository")
public class EbookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookingApplication.class, args);
        DBConnection connect = new DBConnection();
        userDataAccess uda = new userDataAccess();
        uda.isUser("ewm92737@uga.edu", "TeamB6");
    }

}
