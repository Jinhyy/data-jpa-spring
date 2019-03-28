package me.jinhyun.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

// 23-1. JpaRepository 상속받는 인터페이스 정의
@NoRepositoryBean
public interface CustomRepository<T, Id extends Serializable> extends JpaRepository <T, Id> {

    boolean contains(T Entity);

}
