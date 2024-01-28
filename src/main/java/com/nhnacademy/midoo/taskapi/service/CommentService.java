package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.CommentRequest;
import com.nhnacademy.midoo.taskapi.domain.CommentResponse;
import java.util.List;

public interface CommentService {
    List<CommentResponse> getComments(Long taskId);

    CommentResponse getComment(Long commentId);

    CommentResponse createComment(Long taskId, CommentRequest commentRequest);

    CommentResponse modifyComment(Long commentId, CommentRequest commentRequest);

    void deleteComment(Long commentId);
}
