package com.learn.restpg.Repository;

import com.learn.restpg.Model.UserInfo;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserInfo,String>{
    @Modifying
    @Query(value="insert into users values(:name,:pass,true);insert into authorities values(:name,:role)")
    void adduser(@Param("name") String name,@Param("pass") String pass,@Param("role") String role);

}
