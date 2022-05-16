package com.example.springJPA.api;

import com.example.springJPA.exceptions.InvalidUniversityClassException;
import com.example.springJPA.exceptions.StudentEmptyNameException;
import com.example.springJPA.model.Student;
import com.example.springJPA.model.UniversityClass;
import com.example.springJPA.service.UniversityClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/class")
public class UniversityClassController {

    private UniversityClassService universityClassService;

    @Autowired
    public UniversityClassController(UniversityClassService universityClassService) {
        this.universityClassService = universityClassService;
    }

    @GetMapping
    public List<UniversityClass> getAllClasses() {
        return universityClassService.getAllClasses();
    }

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody UniversityClass universityClass) {
        try {
            UniversityClass saveUniversityClass = universityClassService.addClass(universityClass);
            return ResponseEntity.ok("Add Class. " + saveUniversityClass.toString());
        } catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
