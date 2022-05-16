package com.example.springJPA.model;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "university_class_id")
    private UniversityClass universityClass; // 多對一 到班級資料表 (此欄位是要存放完整的一筆班級資料表)

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(UniversityClass universityClass) {
        this.universityClass = universityClass;
    }

    public Student() {
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String str = "";
        str += " Primary ID: " + getId();
        str += " Name: " + getName();
        return str;
    }
}
