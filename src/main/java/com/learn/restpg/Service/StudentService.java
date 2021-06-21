package com.learn.restpg.Service;

import java.util.ArrayList;
import java.util.List;
import com.learn.restpg.Model.Student;
import com.learn.restpg.Repository.StudentRepo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class StudentService {
    private StudentRepo studentRepo;

    public Outcome<?> getbyid(int sid) {
        log.info("Finding student");
        try {
            log.trace("Student found.");
            return Outcome.builder().status("Success").response("Found").data(studentRepo.findById(sid).orElseThrow())
                    .build();
        } catch (Exception e) {
            log.trace(e.toString());
            return Outcome.builder().status("Failed").response("Not found").data(null).build();
        }
    }

    public Outcome<?> getall() {
        log.info("Fetching all students.");
        List<Student> students = new ArrayList<>();
        studentRepo.findAll().forEach(students::add);
        return Outcome.builder().status("Succes").data(students).response("Students found:" + students.size()).build();
    }

    public Outcome<?> saveStudent(Student student) {
        log.info("Saving student in DB");
        try {
            log.trace("Saved!");
            return Outcome.builder().status("Success").response("Saved in DB").data(studentRepo.save(student)).build();
        } catch (Exception e) {
            log.trace(e.toString());
            return Outcome.builder().status("Failed").response("Error").data(studentRepo.save(student)).build();
        }
    }

    public Outcome<?> delete(int sid) {
        log.info("Searcing student to delete.");
        try {
            Student student = studentRepo.findById(sid).orElseThrow();
            log.trace("Deleted");
            return Outcome.builder().status("Success").response("Deleted").data(student).build();
        } catch (Exception e) {
            log.trace(e.toString());
            return Outcome.builder().status("Failed").response("not found").data(null).build();
        }
        // if (student.getSid() != -1) {
        // log.info("Student deleted");
        // studentRepo.delete(student);
        // return "deleted" + " " + student.getSname();
        // } else{
        // log.info("Student not found.");
        // return "not found";
        // }
    }

    public Outcome<?> updateStudent(Student student) {
        log.info("Updating student with data "+student.toString());
        try{
            log.trace("Student found to update.");
            studentRepo.findById(student.getSid()).orElseThrow();
            return Outcome.builder().status("Success").response("Info Updated").data(studentRepo.save(student)).build();
        }
        catch(Exception e){
            log.warn("Student don't exist.");
            return Outcome.builder().status("Failed").response("No such entry").data(student).build();
        }
        // if(studentRepo.findById(student.getSid()).isPresent()){
        //     log.info("Student found to update");
        //     return studentRepo.save(student);
        // }
        // else{
        //     log.info("Student not found.");
        //     return new Student(-1,"Not found",-1);
        // }
    }

}
