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
public class TaskResponse {
    @NotNull
    private Long taskId;

    @NotBlank
    @Size(max = 100)
    private String taskTitle;

    @NotBlank
    private String taskContent;

    @NotNull
    private Long projectId;

    @NotNull
    private Long milestoneId;

    @NotBlank
    @Size(max = 50)
    private String accountId;

    public static TaskResponse fromEntity(Task task) {
        return TaskResponse.builder()
                .taskId(task.getTaskId())
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .projectId(task.getProject().getProjectId())
                .milestoneId(task.getMilestone().getMilestoneId())
                .accountId(task.getAccountId())
                .build();
    }
}
