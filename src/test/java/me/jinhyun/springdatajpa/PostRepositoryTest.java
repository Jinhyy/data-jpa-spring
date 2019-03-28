package me.jinhyun.springdatajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired  // 24-3. applicationContext안에 EventPublisher 있으므로 받아온다.
    ApplicationContext applicationContext;

    @Test   // 24-4. 테스트 코드 작성
    public void eventTest(){
        Post post = new Post();
        post.setTitle("Post-Event-Title");
        PostPublishedEvent postPublishedEvent = new PostPublishedEvent(post);

        applicationContext.publishEvent(postPublishedEvent);    // 이벤트 발생시킨다.
    }

    @Test   // AbstractAggregation 상속 이용
    public void eventTest2(){
        Post post = new Post();
        post.setTitle("Aggregation 상속 구현체 이용");
        postRepository.save(post.publish());
    }

    @Test
    public void crudTest(){
        // Given
        Post post = new Post();
        post.setTitle("test-title");
        assertThat(post.getId()).isNull();

        // When
        Post savedPost = postRepository.save(post);

        // Then
        assertThat(savedPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();

        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.contains(savedPost));

        // When
        Page<Post> postPage = postRepository.findAll(PageRequest.of(0,10));

        // Then
        assertThat(postPage.getTotalElements()).isEqualTo(1);
        assertThat(postPage.getTotalPages()).isEqualTo(1);
        assertThat(postPage.getSize()).isEqualTo(10);
        assertThat(postPage.getNumberOfElements()).isEqualTo(1);

        // When
        Page<Post> postPage2 = postRepository.findByTitleContains("test-title", PageRequest.of(0,10));

        // Then
        assertThat(postPage2.getTotalElements()).isEqualTo(1);
        assertThat(postPage2.getTotalPages()).isEqualTo(1);
        assertThat(postPage2.getSize()).isEqualTo(10);
        assertThat(postPage2.getNumberOfElements()).isEqualTo(1);

        // When
        Page<Post> postPage3 = postRepository.findAllByTitle("test-title", PageRequest.of(0,10));

        // Then
        assertThat(postPage3.getTotalElements()).isEqualTo(1);

    }
}