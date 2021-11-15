package com.example.studentmanagement.service;

import com.example.studentmanagement.dao.UniveristyClassDao;
import com.example.studentmanagement.excepetions.InvalidUnviersityClassException;
import com.example.studentmanagement.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UniveristyClassService {

    UniveristyClassDao universityClassDao;

    @Autowired
    public UniveristyClassService(UniveristyClassDao universityClassDao) {
        this.universityClassDao = universityClassDao;
    }

    public List<UniversityClass> getAllClass(){
        return (List<UniversityClass>) universityClassDao.findAll();

    }

    public UniversityClass addClass(UniversityClass universityClass){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if(universityClass.getYear() < currentYear){
            throw new InvalidUnviersityClassException("Cannot add class in the past");
        }

        if(universityClass.getYear() > currentYear + 1){
            throw new InvalidUnviersityClassException("too far future");
        }

        return universityClassDao.save(universityClass);
    }
}
