package com.example.studentmanagement.service;

import com.example.studentmanagement.dao.StudentDao;
import com.example.studentmanagement.dao.UniveristyClassDao;
import com.example.studentmanagement.excepetions.InvalidUnviersityClassException;
import com.example.studentmanagement.excepetions.StudentEmptyNameException;
import com.example.studentmanagement.excepetions.StudentNonExistException;
import com.example.studentmanagement.mapper.StudentMapper;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentDao studentDao;
    private UniveristyClassDao univeristyClassDao;
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentDao studentDao, UniveristyClassDao univeristyClassDao, StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.univeristyClassDao = univeristyClassDao;
        this.studentMapper = studentMapper;
    }




    public Student addStudent(Student student){
        if(student.getName().isEmpty()){
            throw new StudentEmptyNameException("Student name cannot be empty");

        }
        return studentDao.save(student);
    }

    public Student updateStudent(Student student){
        if(student.getId() == null || !studentDao.existsById(student.getId())){
            throw new StudentNonExistException("Cant find ID");
        }
        return studentDao.save(student);
    }

    public Student assignClass(Long studentId, Long classId){
        if(!studentDao.existsById(studentId)){
            throw new StudentNonExistException("Cant find student" + studentId);
        }
        if(!univeristyClassDao.existsById((classId))){
            throw new InvalidUnviersityClassException("Cannot find class Id" + classId);
        }

        Student student = getStudentById(studentId).get();
        UniversityClass universityClass = univeristyClassDao.findById(classId).get();

        student.setUniversityClass(universityClass);
        return studentDao.save(student);

    }

    public List<Student> getAllStudents(){

        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id){

        return studentDao.findById(id);
    }

    public List<Student> getStudentByName(String name){
        return studentDao.findAllByName(name);
    }

    public List<Student> getStudentsContainsStrInName(String name){
        return studentMapper.getStudentsContainsStrInName("%" + name + "%");
    }

    public List<Student> getStudentsByYear(int year, int number){
        return studentMapper.getStudentByYearClass(year,number);
    }


}
