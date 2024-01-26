package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.List;
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
public class TaskRequest {
    @NotBlank
    @Size(max = 100) String taskTitle;

    @NotBlank String taskContent;

    @NotNull Project project;

    Milestone milestone;

    List<Tag> tagList;

    @NotBlank
    @Size(max = 50) String accountId;

    public static Task toEntity(TaskRequest taskRequest) {
        return Task.builder()
                .taskTitle(taskRequest.getTaskTitle())
                .taskContent(taskRequest.getTaskContent())
                .project(taskRequest.getProject())
                .milestone(taskRequest.getMilestone())
                .accountId(taskRequest.getAccountId())
                .build();
    }
}
