package me.jinhyun.springdatajpa;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

// 23-2. 기본 구현체(SimpleJpaRepository)를 상속받는 커스텀 구현체 만들기
public class CustomRepositoryImpl<T, Id extends Serializable> extends SimpleJpaRepository<T, Id> implements CustomRepository<T,Id>{

    private EntityManager entityManager;

    // 부모에 super를 추가할 때 두개의 인자가 필요하기 때문에 인자만드는
    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager=entityManager;
    }

    @Override
    public boolean contains(T Entity) {
        return entityManager.contains(Entity);
    }
}
