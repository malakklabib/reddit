package com.example.reddit;

import com.example.reddit.domain.*;
import com.example.reddit.repository.CommentRepository;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.reddit.repository.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringitApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }

    @Bean
    PrettyTime prettyTime(){
        return new PrettyTime();
    }


}
