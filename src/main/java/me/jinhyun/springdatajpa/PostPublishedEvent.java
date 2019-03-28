package me.jinhyun.springdatajpa;

import org.springframework.context.ApplicationEvent;

public class PostPublishedEvent extends ApplicationEvent {

    // 24-1. 포스트 도메인 기반 이벤트이므로 선언해줌
    private final Post post;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PostPublishedEvent(Object source) {
        super(source);
        this.post= (Post) source;
        // 24-2. post = 이벤트 발생시키는 source 로 설정한다.
    }

    public Post getPost() {
        return post;
    }
}
