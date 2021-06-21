package com.learn.restpg.Service;

import lombok.Builder;
import lombok.Data;


@Data
@Builder(toBuilder = true)
public class Outcome<T> {
    private String status;
    private String response;
    private T data;
}
