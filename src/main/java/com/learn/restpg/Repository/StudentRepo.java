package com.learn.restpg.Repository;

import com.learn.restpg.Model.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<Student,Integer>{
    // @Query("insert into student (sname,sclass) values (:sname,:sclass) returning *")
    // Student save(@Param("sname") String sname);
    // @Query("insert into users values(:username,:password,true);insert")
    // void adduser()
}