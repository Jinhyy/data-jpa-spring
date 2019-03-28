package me.jinhyun.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends MyRepository<Comment, Long>{

    @Async
    ListenableFuture<List<Comment>> findCommentsByCommentContains(String comment);

    // Pageable 형태로 주면 페이지 정보(전체 몇개요청, 그 중 현재 몇개가 들어있고, ...)
    Page<Comment> findCommentsByLikeCountGreaterThan(int likeCount, Pageable pageable);

    List<Comment> findByCommentContainsIgnoreCase(String keyword);

    Stream<Comment> findCommentsByLikeCountIsLessThanEqual(int likeCount);
}
