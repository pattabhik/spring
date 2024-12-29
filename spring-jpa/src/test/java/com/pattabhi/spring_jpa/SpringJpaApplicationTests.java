package com.pattabhi.spring_jpa;

import com.pattabhi.spring_jpa.entities.Student;
import com.pattabhi.spring_jpa.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJpaApplicationTests {
    @Autowired
    StudentRepository studentRepository;

    @Test
    void testSaveStudent() {
        Student newStudent = new Student();
        newStudent.setName("Pattabhi");
        newStudent.setId(100L);
        newStudent.setTestScore(100);
		//save
        studentRepository.save(newStudent);
        Student student = studentRepository.findById(100L).get();
        Assertions.assertNotNull(student);

        student.setName("Pattabhi Ramaiah");
        //updating the stuent
        studentRepository.save(student);
        Assertions.assertEquals("Pattabhi Ramaiah", student.getName());
        //delete
        studentRepository.delete(student);
        Assertions.assertFalse(studentRepository.existsById(100L));
    }

}
