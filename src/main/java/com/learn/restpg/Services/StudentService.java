package com.learn.restpg.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Student> getbyid(int sid) {
        log.info("Finding student");
        return studentRepo.findById(sid);
    }

    public List<Student> getall() {
        log.info("Fetching all students.");
        List<Student> students = new ArrayList<>();
        studentRepo.findAll().forEach(students::add);
        return students;
    }

    public Student saveStudent(Student student) {
        log.info("Saving student in DB");
        return studentRepo.save(student);
    }

    public String delete(int sid) {
        log.info("Searcing student to delete.");
        Student student = studentRepo.findById(sid).orElse(new Student(-1,"",-1));
        if (student.getSid() != -1) {
            log.info("Student deleted");
            studentRepo.delete(student);
            return "deleted" + " " + student.getSname();
        } else{
            log.info("Student not found.");
            return "not found";
        }
    }

    public Student updateStudent(Student student) {
        log.info("Updating student with data"+student.toString());
        if(studentRepo.findById(student.getSid()).isPresent()){
            log.info("Student found to update");
            return studentRepo.save(student);
        }
        else{
            log.info("Student not found.");
            return new Student(-1,"Not found",-1);
        }
    }


}
