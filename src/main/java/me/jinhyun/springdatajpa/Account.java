package me.jinhyun.springdatajpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "newAccount")
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    private String yes;

    @Transient  // 이건 테이블에 매핑되지 않는다.
    private String no;

    @Embedded   // Composite Value
    @AttributeOverrides({   // 속성 변경가능.
            @AttributeOverride(name="street", column = @Column(name="home_street"))
    })
    private Address homeAddress;

}
