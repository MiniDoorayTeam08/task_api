package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    Long commentId;
    String commentContent;
    Long taskId;
    String accountId;

    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .commentContent(comment.getCommentContent())
                .taskId(comment.getTask().getTaskId())
                .accountId(comment.getAccountId())
                .build();
    }
}
