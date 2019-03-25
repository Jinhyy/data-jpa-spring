package me.jinhyun.springdatajpa;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional  // entity와 관련된 모든 액션은 transactional 해야 하므로
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Post post= new Post();
        post.setTitle("포스트 제목");

        Comment comment1 = new Comment();
        comment1.setComment("코멘트1");

        Comment comment2= new Comment();
        comment2.setComment("코멘트2");

        post.addComment(comment1);
        post.addComment(comment2);

        /*
            테스트1 : entityManager를 사용

        Session session = entityManager.unwrap(Session.class);
        session.save(post);

        TypedQuery<Post> query= entityManager.createQuery("select p from Post AS p", Post.class);
        List<Post> posts = query.getResultList();
        posts.forEach(c->{
            System.out.println(c.toString());
        });
        */

        /*
            테스트2 : repository 사용
         */

        postRepository.save(post);
        postRepository.findAll().forEach(System.out::println);
    }
}
