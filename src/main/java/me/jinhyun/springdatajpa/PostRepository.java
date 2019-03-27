package me.jinhyun.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

    // 타이틀에 특정 키워드를 가지고 있는 포스트를 페이징을 통하여 찾겠다.
    // 구현은 메소드 이름 분석하여 자동으로 된다.
    Page<Post> findByTitleContains(String title, Pageable pageable);

    Page<Post> findAllByTitle(String notitle, Pageable pageable);

}
