package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Comment;
import com.nhnacademy.midoo.taskapi.entity.Task;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@Getter
public class CommentResponse {
    @NotNull
    Long commentId;

    @NotBlank
    String commentContent;

    @NotNull
    Long taskId;

    @NotBlank
    @Size(max = 50)
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
