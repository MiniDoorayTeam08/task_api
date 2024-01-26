package com.nhnacademy.midoo.taskapi.exception;

import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;

public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException(BindingResult bindingResult) {
        super(bindingResult.getFieldErrors()
                .stream()
                .map(error -> new StringBuilder().append("Field=").append(error.getField())
                        .append(", Message=").append(error.getDefaultMessage()))
                .collect(Collectors.joining(" | ")));
    }
}
