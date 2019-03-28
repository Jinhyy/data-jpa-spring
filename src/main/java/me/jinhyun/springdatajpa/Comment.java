package me.jinhyun.springdatajpa;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Getter @Setter
public class Comment {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Post post;

    private String comment;

    private int likeCount;

    public boolean likeCountBiggerThan(int likeCount){
        return ( this.likeCount > likeCount) ? true : false;
    }

}
