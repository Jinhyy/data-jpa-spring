package me.jinhyun.springdatajpa;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean   // 아무 기능이 없는 Repository
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {

    // T의 하위타입도 지원하겠다. 매개변수는 null이면 안된다.
    <E extends T> E save(@NotNull E entity);
    List<T> findAll();
    long count();

    @Nullable // 이 메소드의 리턴값이 null 일 수 있다.
    <E extends T> Optional<E> findById(@NotNull Id id);
}
