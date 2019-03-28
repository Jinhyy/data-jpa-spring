package me.jinhyun.springdatajpa;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// 24-5. 포스트 관련 이벤트 리스너 설정(이벤트 받았을 때 처리하는)

@Component
public class PostEventListener implements ApplicationListener<PostPublishedEvent> {

    @Override
    public void onApplicationEvent(PostPublishedEvent event) {
        System.out.println("======POST EVENT OCCUR========");
        System.out.println(event.getPost().getTitle());
        System.out.println("============================");
    }
}
