package com.example.studentmanagement.api;

import com.example.studentmanagement.excepetions.InvalidUnviersityClassException;
import com.example.studentmanagement.service.StudentService;
import com.example.studentmanagement.excepetions.StudentEmptyNameException;
import com.example.studentmanagement.model.Student;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequiresPermissions("student:reader")
    public List<Student> getAllStudents(){
        return  studentService.getAllStudents();
    }

    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student){
        try{
            Student saveStudent = studentService.addStudent(student);
            return ResponseEntity.ok("Registered student: " + student.toString());
        }catch(StudentEmptyNameException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "assignclass/{sid}/{cid}")
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId){
        try{
            Student updateStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("Assigned! " + updateStudent.toString());
        }catch(StudentEmptyNameException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch(
            InvalidUnviersityClassException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/name")
    public List<Student> getStudents(@RequestParam String name){
        return studentService.getStudentByName(name);
    }

    @GetMapping("/contain_name")
    public List<Student> getStudentsContainName(@RequestParam String name){
        return studentService.getStudentsContainsStrInName(name);
    }

    @GetMapping("/class")
    public List<Student> getStudentByClass(@RequestParam int year,
                                           @RequestParam int number){
        return studentService.getStudentsByYear(year, number);
    }



}
