package me.jinhyun.springdatajpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "account")
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    private String yes;

    @Transient  // 이건 테이블에 매핑되지 않는다.
    private String no;

    @Embedded   // Composite Value
    @AttributeOverrides({   // 속성 오버라이딩 가능.
            @AttributeOverride(name="street", column = @Column(name="home_street"))
    })
    private Address homeAddress;

    /*
        Account 1 -> Study 多
     */
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this);
    }

    public void removeStudy(Study study){
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
