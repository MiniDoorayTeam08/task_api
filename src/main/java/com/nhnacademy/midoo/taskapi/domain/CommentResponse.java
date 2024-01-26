package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Comment;
import com.nhnacademy.midoo.taskapi.entity.Task;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    @NotNull
    private Long commentId;

    @NotBlank
    private String commentContent;

    @NotNull
    private Long taskId;

    @NotBlank
    @Size(max = 50)
    private String accountId;

    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .commentContent(comment.getCommentContent())
                .taskId(comment.getTask().getTaskId())
                .accountId(comment.getAccountId())
                .build();
    }
}
