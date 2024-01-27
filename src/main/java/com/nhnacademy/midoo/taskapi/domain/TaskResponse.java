package com.nhnacademy.midoo.taskapi.domain;

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

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {
    @NotNull
    Long taskId;

    @NotBlank
    @Size(max = 100)
    String taskTitle;

    @NotBlank
    String taskContent;

    @NotNull
    Long projectId;

    @NotNull
    Long milestoneId;

    @NotBlank
    @Size(max = 50)
    String accountId;

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
