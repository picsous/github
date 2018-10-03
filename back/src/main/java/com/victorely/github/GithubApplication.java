package com.victorely.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GithubApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(GithubApplication.class, args);
        DatabaseSeeder tdbs = ac.getBeanFactory().createBean(DatabaseSeeder.class);
        tdbs.seedDatabase();
    }

}
