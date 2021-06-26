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
        log.trace("Finding student");
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
        log.trace("Fetching all students.");
        List<Student> students = new ArrayList<>();
        studentRepo.findAll().forEach(students::add);
        return Outcome.builder().status("Succes").data(students).response("Students found:" + students.size()).build();
    }

    public Outcome<?> saveStudent(Student student) {
        log.trace("Saving student in DB");
        try {
            log.trace("Saved!");
            return Outcome.builder().status("Success").response("Saved in DB").data(studentRepo.save(student)).build();
        } catch (Exception e) {
            log.warn(e.toString());
            return Outcome.builder().status("Failed").response("Error").data(studentRepo.save(student)).build();
        }
    }

    public Outcome<?> delete(int sid) {
        log.trace("Searcing student to delete.");
        try {
            Student student = studentRepo.findById(sid).orElseThrow();
            studentRepo.delete(student);
            log.trace("Deleted");
            return Outcome.builder().status("Success").response("Deleted").data(student).build();
        } catch (Exception e) {
            log.warn(e.toString());
            return Outcome.builder().status("Failed").response("not found").data(null).build();
        }
    }

    public Outcome<?> updateStudent(Student student) {
        log.trace("Updating student with data "+student.toString());
        try{
            log.trace("Student found to update.");
            studentRepo.findById(student.getSid()).orElseThrow();
            return Outcome.builder().status("Success").response("Info Updated").data(studentRepo.save(student)).build();
        }
        catch(Exception e){
            log.warn("Student don't exist.");
            return Outcome.builder().status("Failed").response("No such entry").data(student).build();
        }
    }
}
