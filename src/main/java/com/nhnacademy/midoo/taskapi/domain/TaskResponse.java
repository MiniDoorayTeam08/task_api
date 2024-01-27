package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
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

    Long milestoneId;

    @NotBlank
    @Size(max = 50)
    String accountId;

    public static TaskResponse fromEntity(Task task) {
        TaskResponse taskResponse = TaskResponse.builder()
                .taskId(task.getTaskId())
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .projectId(task.getProject().getProjectId())
                .accountId(task.getAccountId()).build();
        if(!Objects.isNull(task.getMilestone())){
            taskResponse.builder()
                    .milestoneId(task.getMilestone().getMilestoneId()).build();
        }
        return taskResponse;
    }
}
