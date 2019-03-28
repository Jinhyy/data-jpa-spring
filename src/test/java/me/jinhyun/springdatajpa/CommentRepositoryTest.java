package me.jinhyun.springdatajpa;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.swing.text.html.Option;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() throws InterruptedException, ExecutionException, TimeoutException {
        // Given
        Comment comment = new Comment();
        comment.setComment("comment-comment1");
        commentRepository.save(comment);

        Comment comment2 = new Comment();
        comment2.setComment("comment-comment2");
        commentRepository.save(comment2);

        // Then
        List<Comment> allComment = commentRepository.findAll();
        assertThat(allComment.size()).isEqualTo(2);
        assertThat(commentRepository.count()).isEqualTo(2l);

        // Then 메소드 쿼리 테스트
        ListenableFuture<List<Comment>> future =
                commentRepository.findCommentsByCommentContains("comment1");
        System.out.println("===================================");
        System.out.println("is done? " + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("비동기콜 실패" + ex);
            }

            @Override
            public void onSuccess(List<Comment> result) {
                System.out.println("비동기콜 성공");
                result.forEach(x-> System.out.println(x.getComment()));
            }
        });
        /*
            List<Comment> comments = future.get(10l, TimeUnit.SECONDS);
            comments.forEach(x-> System.out.println(x.getComment()));
       */

    }

    @Test
    public void methodQueryTest(){
        // Given
        Comment comment = new Comment();
        comment.setComment("comment-comment1");
        comment.setLikeCount(1);
        commentRepository.save(comment);

        Comment comment2 = new Comment();
        comment2.setLikeCount(6);
        comment2.setComment("comment-comment2");
        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setComment("comment-comment3");
        comment.setLikeCount(11);
        commentRepository.save(comment3);

        PageRequest pageRequest = PageRequest.of(0,10,Sort.by(Sort.Direction.DESC,"likeCount"));

        // Then
        List<Comment> commentList = commentRepository.findByCommentContainsIgnoreCase("Comment3");
        assertThat(commentList.contains(comment3)).isTrue();

        // Then
        Page<Comment> commentPage =
                commentRepository.findCommentsByLikeCountGreaterThan(5, pageRequest);
        assertThat(commentPage.getTotalElements()).isEqualTo(2l);
        assertThat(commentPage).first().hasFieldOrPropertyWithValue("likeCount",11);

        // Then
        Stream<Comment> streamComment = commentRepository.findCommentsByLikeCountIsLessThanEqual(11);
        long a = streamComment.filter(x-> x.likeCountBiggerThan(5)).count();
        assertThat(a).isEqualTo(2l);
    }

}


