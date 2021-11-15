package com.example.studentmanagement.model;

import org.springframework.expression.spel.ast.NullLiteral;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "university_class")
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    Integer year;

    @Column(nullable = false)
    Integer number;

//    @OneToMany(mappedBy = "UniversityClass")
//    List<Student> students;

    public UniversityClass() {}

//    @OneToMany(mappedBy = "UniveristyClass")
//    List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Primary Id: " + getId();
        str += " Year: "  +getYear();
        str += " Number: " + getNumber();
        return str;
    }
}
