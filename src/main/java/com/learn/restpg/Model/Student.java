package com.learn.restpg.Model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Integer sid;
    private String sname;
    // private String firstName;
    // private String lastName;
    private Integer sclass;
    // private Integer monthlyFees;
}
