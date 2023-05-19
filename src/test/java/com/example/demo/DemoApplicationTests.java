package com.example.demo;

import com.alibaba.fastjson2.JSON;
import com.example.demo.dao.jpa.*;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.val;
import org.apache.lucene.index.DocIDMerger;
import org.elasticsearch.common.geo.GeoPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class DemoApplicationTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserJpaRepository jpaRepository;

    @Test
    void contextLoads() {
      User user= User.builder()
              .username("test")
                .age(18)
                .province("上海")
                .city("上海")
                .district("浦东新区")
                .address("上海市浦东新区花园石桥路28弄1-8号-汤臣一品")
              .about("槟榔妹真好玩啊")
              .address(JSON.toJSONString(new GeoPoint(31.238794, 121.508506)))
              .build();
      log.info("user:{}",user);
      jpaRepository.save(user);
    }
    @Autowired
    private StudentJpaRepository studentJpaRepository;
    @Autowired
    private TeacherJpaRepository teacherJpaRepository;
    @Autowired
    private CareerJpaRepository careerJpaRepository;
    @Autowired
    private SubjectJpaRepository subjectJpaRepository;
    @Test
    public void testSave(){
        Subject subject=Subject.builder()
                .name("语文")
                .subjectTeachers(new ArrayList<>())
                .build();
       teacherJpaRepository.findById(1L).ifPresent(teacher -> {
           subject.getSubjectTeachers().add(teacher);});
        subjectJpaRepository.save(subject);
    }
    @Test
    @DisplayName("保存老师")
    public void testSaveTeacher(){
        Teacher teacher=Teacher.builder()
                .name("张老师")
                .surname("张")
                .age(18)
                .subjects(new ArrayList<>())
                .address("上海市浦东新区花园石桥路28弄1-8号-汤臣一品")
                .build();
        teacherJpaRepository.save(teacher);
//        val it=teacherJpaRepository.findById(0L);
//        System.out.println(it.get().getSubjects());
    }
    @Test
    @Transactional
    @DisplayName("查询老师")
    public void  readTeacher(){
        val it=subjectJpaRepository.findById(5L);
        List<Teacher> teacherList=it.get().getSubjectTeachers();
        for (Teacher teacher:teacherList){
            System.out.println(teacher);
        }
    }
    @Test
    public  void deleteSubject(){
        subjectJpaRepository.deleteById(4L);
    }
}
