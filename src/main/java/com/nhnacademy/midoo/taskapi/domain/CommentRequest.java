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
public class CommentRequest {
    @NotBlank
    String commentContent;

    @NotBlank
    @Size(max = 50)
    String accountId;

    public static Comment toEntity(CommentRequest commentRequest) {
        return Comment.builder()
                .commentContent(commentRequest.getCommentContent())
                .accountId(commentRequest.getAccountId())
                .build();
    }
}
