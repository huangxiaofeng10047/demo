package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hxf
 * @date 2023年05月18日 14:12
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {
@Id
@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
@Column(name ="subject_id")
private Long id;
private String name;
@ManyToMany
@JoinTable(name = "subject_student",
        joinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "subject_id")},
        inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "student_id")})
@JsonIgnoreProperties("subjects")
@ToString.Exclude
private List<Student> students=new ArrayList<>();
@ManyToMany(mappedBy = "subjects")
@JsonIgnoreProperties("subjects")
@ToString.Exclude
private List<Career> subjectCareers=new ArrayList<>();
@ManyToMany
@JoinTable(name = "subject_teacher",
        joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subject_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id"))
@ToString.Exclude
private List<Teacher>subjectTeachers=new ArrayList<>();
//1、关系维护端，负责多对多关系的绑定和解除
//2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
//3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Authority)
//4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
//即表名为user_authority
//关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
//关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
//主表就是关系维护端对应的表，从表就是关系被维护端对应的表
//1、多对多关系中一般不设置级联保存、级联删除、级联更新等操作。
//
//2、可以随意指定一方为关系维护端，在这个例子中，我指定 User 为关系维护端，所以生成的关联表名称为： user_authority，关联表的字段为：user_id 和 authority_id。
//
//3、多对多关系的绑定由关系维护端来完成，即由 User.setAuthorities(authorities) 来绑定多对多的关系。关系被维护端不能绑定关系，即Game不能绑定关系。
//
//4、多对多关系的解除由关系维护端来完成，即由Player.getGames().remove(game)来解除多对多的关系。关系被维护端不能解除关系，即Game不能解除关系。
//
//5、如果 User 和 Authority 已经绑定了多对多的关系，那么不能直接删除 Authority，需要由 User 解除关系后，才能删除 Authority。但是可以直接删除 User，因为 User 是关系维护端，删除 User 时，会先解除 User 和 Authority 的关系，再删除 Authority。
@OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@ToString.Exclude
private List<Score>scores=new ArrayList<>();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Subject subject = (Subject) o;
//        return getId() != null && Objects.equals(getId(), subject.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
