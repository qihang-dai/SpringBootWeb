package com.example.studentmanagement.api;

import com.example.studentmanagement.excepetions.InvalidUnviersityClassException;
import com.example.studentmanagement.excepetions.StudentEmptyNameException;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.UniversityClass;
import com.example.studentmanagement.service.UniveristyClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/class")
public class UniveristyClassController {

    private UniveristyClassService univeristyClassService;

    @Autowired
    public UniveristyClassController(UniveristyClassService univeristyClassService) {
        this.univeristyClassService = univeristyClassService;
    }


    @GetMapping
    List<UniversityClass> getAllClasses(){
        return univeristyClassService.getAllClass();
    }

    @RequestMapping("/add")
    @PostMapping
    public ResponseEntity<String> addClass(@RequestBody UniversityClass universityClass){
        try{
            UniversityClass saveUniversityClass = univeristyClassService.addClass(universityClass);
            return ResponseEntity.ok("Added class: " + saveUniversityClass.toString());
        }catch(InvalidUnviersityClassException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
