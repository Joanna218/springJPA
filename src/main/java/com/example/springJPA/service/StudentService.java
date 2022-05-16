package com.example.springJPA.service;

import com.example.springJPA.dao.StudentDao;
import com.example.springJPA.dao.UniversityClassDao;
import com.example.springJPA.exceptions.InvalidUniversityClassException;
import com.example.springJPA.exceptions.StudentEmptyNameException;
import com.example.springJPA.exceptions.StudentNonExistException;
import com.example.springJPA.mapper.StudentMapper;
import com.example.springJPA.model.Student;
import com.example.springJPA.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService  {

    private StudentDao studentDao;
    private UniversityClassDao universityClassDao;

    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentDao studentDao, UniversityClassDao universityClassDao, StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.universityClassDao = universityClassDao;
        this.studentMapper = studentMapper;
    }

    public Student addStudent(Student student) {
        if (student.getName().isEmpty()) {
            throw new StudentEmptyNameException("Student name cannot be empty!");
        }

        return studentDao.save(student);
    }

    public Student updateStudent(Student student) {
        if (student.getId() == null || !studentDao.existsById(student.getId())) {
            throw new StudentNonExistException("Cannot find student Id!");
        }

        return studentDao.save(student);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }

    public Student assignClass(Long studentId, Long classId) {
        if (!studentDao.existsById(studentId)) {
            throw new StudentNonExistException("Cannot find student Id " + studentId);
        }

        if (!universityClassDao.existsById(classId)) {
            throw new InvalidUniversityClassException("Cannot find class Id " + classId);
        }

        Student student = getStudentById(studentId).get(); // 得到學生資料
        UniversityClass universityClass = universityClassDao.findById(classId).get(); // 得到班級資料
        student.setUniversityClass(universityClass); // 班級資料整筆 set 到 學生資料表中的欄位

        return studentDao.save(student);
    }

    public List<Student> getStudentsByName(String name) {
        return studentDao.findByName(name);
    }

    public List<Student> getStudentsContainStrInName(String name) {
        return studentMapper.getStudentsContainStrInName('%' + name + '%');
    }

    public List<Student> getStudentsInClass(int year, int number) {
        return studentMapper.getStudentsInClass(year, number);
    }
}
