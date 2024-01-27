package com.nhnacademy.midoo.taskapi.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetailResponse {
    TaskDto task;
    List<TagDto> tags;
    List<CommentDto> comments;
}
