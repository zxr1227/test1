package com.example.demo.util;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DataVO<T>{
    private Integer code = 200;
    private String message = "success";
    private T data;

    public DataVO(T data) {
        this.data = data;
    }
}
