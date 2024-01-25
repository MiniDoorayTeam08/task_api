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
public class CommentRequest {
    @NotBlank
    private String commentContent;

    @NotBlank
    @Size(max = 50)
    private String accountId;

    public static Comment toEntity(CommentRequest commentRequest) {
        return Comment.builder()
                .commentContent(commentRequest.getCommentContent())
                .accountId(commentRequest.getAccountId())
                .build();
    }
}
