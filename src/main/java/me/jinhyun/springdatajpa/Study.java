package me.jinhyun.springdatajpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Getter @Setter
public class Study {
    @Id @GeneratedValue
    private long id;

    private String name;

    /*
        Account 1개가 Study 여러개 만들 수 있으므로
        ManyToOne 어노테이션은 Account의 키를 외래키로 가져온다.
     */
    @ManyToOne
    private Account owner;

}
