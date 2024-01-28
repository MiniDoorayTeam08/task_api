package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.CommentRequest;
import com.nhnacademy.midoo.taskapi.domain.CommentResponse;
import com.nhnacademy.midoo.taskapi.exception.ValidationFailedException;
import com.nhnacademy.midoo.taskapi.service.CommentService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentRestController {
    private final CommentService commentService;

    @GetMapping("/{taskId}")
    public List<CommentResponse> getComments(@PathVariable Long taskId) {
        return commentService.getComments(taskId);
    }

    @GetMapping("/{commentId}/comment")
    public CommentResponse getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/{taskId}/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse registerComment(@PathVariable Long taskId,
                                           @Valid @RequestBody CommentRequest commentRequest,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return commentService.createComment(taskId, commentRequest);
    }

    @PutMapping("/{commentId}")
    public CommentResponse modifyComment(@PathVariable Long commentId,
                                         @Valid @RequestBody CommentRequest commentRequest,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return commentService.modifyComment(commentId, commentRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
