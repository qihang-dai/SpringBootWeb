package com.example.studentmanagement.mapper;

import com.example.studentmanagement.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student WHERE name Like #{name}")
    List<Student> getStudentsContainsStrInName(@Param("name") String name);

    @Select("SELECT * FROM student WHERE class_id IN" +
            "(SELECT id from university_class where year = #{year) AND number = #{number}")

    List<Student> getStudentByYearClass(@Param("year") int year, @Param("number") int number);
}
