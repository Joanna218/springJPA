package com.example.springJPA.api;

import com.example.springJPA.exceptions.InvalidUniversityClassException;
import com.example.springJPA.exceptions.StudentEmptyNameException;
import com.example.springJPA.exceptions.StudentNonExistException;
import com.example.springJPA.model.Student;
import com.example.springJPA.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> registerStudents(@RequestBody Student student) {
        try {
            Student saveStudent = studentService.addStudent(student);
            return ResponseEntity.ok("Registered student." + student.toString());
        } catch (StudentEmptyNameException e) { // 若發送無帶資料、名字空的會跑這裡
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 建立好學生與班級後，開始把學生分配給班級
    @PostMapping(path = "assignclass/{sid}/{cid}")
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId) {
        try {
            Student updatedStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("Assign class. " + updatedStudent.toString());
        } catch (StudentNonExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/name")
    // http://localhost:8080/api/student/name?name=Joanna Lin
    public List<Student> getStudents(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/contain_name")
    // http://localhost:8080/api/student/contain_name?name=j
    public List<Student> getStudentsContainName(@RequestParam String name) {
        return studentService.getStudentsContainStrInName(name);
    }

    @GetMapping("/class")
    // http://localhost:8080/api/student/class?year=2022&number=2
    public List<Student> getStudentsInClass(@RequestParam int year, @RequestParam int number) {
        return studentService.getStudentsInClass(year, number);
    }
}
