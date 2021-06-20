package com.learn.restpg.Controller;

import java.util.List;
import java.util.Optional;

import com.learn.restpg.Model.Student;
import com.learn.restpg.Services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    // fetch student by its sid
    @GetMapping(path = "/student/{sid}")
    public ResponseEntity<?> getbyidEntity(@PathVariable("sid") int sid) {
        log.info("Inside the get by ID method.");
        Optional<Student> student = studentService.getbyid(sid);
        log.info("Student found:{}", student);
        return ResponseEntity.ok(student);
    }

    // fetch all students
    @GetMapping("/students")
    public ResponseEntity<?> getallEntity() {
        log.info("Inside get all method");
        List<Student> students = studentService.getall();
        log.info("Found the list of students:{}", students);
        return ResponseEntity.ok(students);
    }

    // create new student in database with form format
    @PostMapping(path = "/student", consumes = { "application/x-www-form-urlencoded", "multipart/form-data" })
    public ResponseEntity<?> saveStudentEntity(Student student) {
        log.info("Inside save student entity.");
        Student studentSaved = studentService.saveStudent(student);
        log.info("Student saved with info:{}", studentSaved);
        return ResponseEntity.ok(studentSaved);
    }

    // create new student in db with json or xml
    @PostMapping(path = "/student", consumes = { "application/json", "application/xml" })
    public ResponseEntity<?> saveStudentInJsonEntity(@RequestBody Student student) {
        log.info("Inside save student in json.");
        Student studentSaved = studentService.saveStudent(student);
        log.info("Student saved with info:{}", studentSaved);
        return ResponseEntity.ok(studentSaved);
    }

    // delete student by their sid
    @DeleteMapping("/student/{sid}")
    public ResponseEntity<?> deleteEntity(@PathVariable("sid") int sid) {
        log.info("Inside delete student entity.");
        String response = studentService.delete(sid);
        log.info("Control returned to the entity.");
        return ResponseEntity.ok(response);
    }

    // update or create new student entry
    @PutMapping(path = "/student", consumes = { "application/x-www-form-urlencoded", "multipart/form-data" })
    public ResponseEntity<?> updateStudentEntity(Student student) {
        log.info("Inside update student entity.");
        Student updatedStudent = studentService.updateStudent(student);
        log.info("Student update finshed.");
        return ResponseEntity.ok(updatedStudent);
    }

    @PutMapping(path = "/student", consumes = { "application/json", "application/xml" })
    public ResponseEntity<?> updateStudentjsonEntity(@RequestBody Student student) {
        log.info("Inside update student entity.");
        Student updatedStudent = studentService.updateStudent(student);
        log.info("Student update finshed.");
        return ResponseEntity.ok(updatedStudent);
    }
}
