package me.jinhyun.springdatajpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {
    @Bean
    public PostEventListener postEventListener(){
        return new PostEventListener();
    }
}
