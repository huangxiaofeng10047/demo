package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

/**
 * @author hxf
 * @date 2023年05月18日 14:26
 */
@Builder
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;
    @Column(name = "social_security_number")
    private String socialSecurityNumber;
    private String name;
    private String surname;
    private String address;
    private Integer age;
    @ManyToMany(mappedBy = "subjectTeachers")
    @JsonIgnoreProperties("subjectTeachers")
    @ToString.Exclude
    private List<Subject> subjects;
    @ManyToMany(mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    @ToString.Exclude
    private List<Career>careers;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Teacher teacher = (Teacher) o;
//        return getId() != null && Objects.equals(getId(), teacher.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
