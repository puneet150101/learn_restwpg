package com.learn.restpg.Controller;

import com.learn.restpg.Model.LoginInfo;
import com.learn.restpg.Model.Student;
import com.learn.restpg.Model.UserInfo;
import com.learn.restpg.Service.LoginService;
import com.learn.restpg.Service.Outcome;
import com.learn.restpg.Service.StudentService;

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

    @Autowired
    private LoginService loginService;
    
    @PostMapping(path="/auth",consumes = {"application/x-www-form-urlencoded","multipart/form-data" })
    public ResponseEntity<?> authEntity(LoginInfo loginInfo) throws Exception{
        log.trace("Inside Generate Jwt api method.");
        return ResponseEntity.ok(loginService.genJwtOutcome(loginInfo));
	}

    @PostMapping(path="/adduser",consumes={"application/x-www-form-urlencoded","multipart/form-data" } )
    public ResponseEntity<?> adduserEntity(UserInfo userInfo){
        log.trace("Inside add user method.");
        return ResponseEntity.ok(loginService.addUser(userInfo));
    }
    
    @PostMapping(path="/adduser",consumes={ "application/json", "application/xml" } )
    public ResponseEntity<?> adduserinjsonEntity(@RequestBody UserInfo userInfo){
        log.trace("Inside add user method.");
        return ResponseEntity.ok(loginService.addUser(userInfo));
    }

    @PostMapping(path="/auth",consumes = { "application/json", "application/xml" })
    public ResponseEntity<?> authbyjsonEntity(@RequestBody LoginInfo loginInfo) throws Exception{
        log.trace("Inside Generate Jwt by Json api method.");
        return ResponseEntity.ok(loginService.genJwtOutcome(loginInfo));
	}

    // fetch student by its sid
    @GetMapping(path = "/student/{sid}")
    public ResponseEntity<?> getbyidEntity(@PathVariable("sid") int sid) {
        log.trace("Inside the get by ID method.");
        return ResponseEntity.ok(studentService.getbyid(sid));
    }

    // fetch all students
    @GetMapping("/students")
    public ResponseEntity<?> getallEntity() {
        log.trace("Inside get all method");
        Outcome<?> outcome = studentService.getall();
        log.info("Found the list of students:{}", outcome.getData());
        return ResponseEntity.ok(outcome);
    }

    // create new student in database with form format
    @PostMapping(path = "/student", consumes = { "application/x-www-form-urlencoded", "multipart/form-data" })
    public ResponseEntity<?> saveStudentEntity(Student student) {
        log.trace("Inside save student entity.");
        Outcome<?> outcome = studentService.saveStudent(student);
        log.info("Student saved with info:{}", outcome.getData());
        return ResponseEntity.ok(outcome);
    }

    // create new student in db with json or xml
    @PostMapping(path = "/student", consumes = { "application/json", "application/xml" })
    public ResponseEntity<?> saveStudentInJsonEntity(@RequestBody Student student) {
        log.trace("Inside save student in json.");
        Outcome<?> outcome = studentService.saveStudent(student);
        log.info("Student saved with info:{}", outcome.getData());
        return ResponseEntity.ok(outcome);
    }

    // delete student by their sid
    @DeleteMapping("/student/{sid}")
    public ResponseEntity<?> deleteEntity(@PathVariable("sid") int sid) {
        log.trace("Inside delete student entity.");
        Outcome<?> outcome = studentService.delete(sid);
        return ResponseEntity.ok(outcome);
    }

    // update or create new student entry
    @PutMapping(path = "/student", consumes = { "application/x-www-form-urlencoded", "multipart/form-data" })
    public ResponseEntity<?> updateStudentEntity(Student student) {
        log.trace("Inside update student entity.");
        Outcome<?> outcome = studentService.updateStudent(student);
        return ResponseEntity.ok(outcome);
    }

    @PutMapping(path = "/student", consumes = { "application/json", "application/xml" })
    public ResponseEntity<?> updateStudentjsonEntity(@RequestBody Student student) {
        log.trace("Inside update student entity.");
        Outcome<?> outcome = studentService.updateStudent(student);
        return ResponseEntity.ok(outcome);
    }
}
