package me.jinhyun.springdatajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

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