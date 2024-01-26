package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.ApiError;
import com.nhnacademy.midoo.taskapi.exception.CommentNotExistException;
import com.nhnacademy.midoo.taskapi.exception.MilestoneNotExistException;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TagNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TaskNotExistException;
import com.nhnacademy.midoo.taskapi.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {
    @ExceptionHandler({CommentNotExistException.class, MilestoneNotExistException.class, ProjectNotExistException.class,
            TagNotExistException.class, TaskNotExistException.class, ValidationFailedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleException(Exception exception) {
        log.error("", exception);

        ApiError apiError = new ApiError();
        apiError.setErrorMessage(exception.getMessage());

        return apiError;
    }

}
