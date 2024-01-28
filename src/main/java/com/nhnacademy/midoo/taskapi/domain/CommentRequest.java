package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Comment;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
