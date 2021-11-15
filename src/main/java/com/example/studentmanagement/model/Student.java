package com.example.studentmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name ="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private UniversityClass universityClass;


    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(){}


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(UniversityClass universityClass) {
        this.universityClass = universityClass;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String str = "";
        str = "Primary ID:" + getId();
        str += " Name: " + getName();
        return str;
    }
}
