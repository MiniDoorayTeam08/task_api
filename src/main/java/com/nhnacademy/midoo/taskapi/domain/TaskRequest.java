package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
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
public class TaskRequest {
    @NotBlank
    @Size(max = 100)
    private String taskTitle;

    @NotBlank
    private String taskContent;

    @NotNull
    private Project project;

    private Milestone milestone;

    @NotBlank
    @Size(max = 50)
    private String accountId;

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
