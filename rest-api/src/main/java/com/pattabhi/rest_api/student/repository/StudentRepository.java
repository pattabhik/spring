package com.pattabhi.rest_api.student.repository;

import com.pattabhi.rest_api.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    // You can define custom query methods if needed, for example:
    // List<Student> findByFirstName(String firstName);
}