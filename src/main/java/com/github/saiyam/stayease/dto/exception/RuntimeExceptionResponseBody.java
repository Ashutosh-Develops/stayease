package com.github.saiyam.stayease.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RuntimeExceptionResponseBody {

    private String message;

    public static RuntimeExceptionResponseBody getRuntimeExceptionResponseBody(RuntimeException e){
        return new RuntimeExceptionResponseBody(e.getMessage());
    }
}
