package com.example.reddit;

import com.example.reddit.domain.*;
import com.example.reddit.repository.CommentRepository;
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
    CommandLineRunner runner(LinkRepository lr, CommentRepository cr){
        return args -> {
            Link link = new Link("getting started with spring boot", "https://therealdanvega.com/spring-boot-2");
            lr.save(link);

            Comment comment = new Comment("this is a comment", link);
            cr.save(comment);
            link.addComment(comment);
        };
    }
}
