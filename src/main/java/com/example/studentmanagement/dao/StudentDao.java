package com.example.studentmanagement.dao;

import com.example.studentmanagement.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentDao extends CrudRepository<Student, Long> {

    List<Student> findAllByName(String name);

}
