package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class TaskResponse {
    Long taskId;
    String taskTitle;
    String taskContent;
    Long projectId;
    Long milestoneId;
    String accountId;

    public static TaskResponse fromEntity(Task task) {
        TaskResponse taskResponse = TaskResponse.builder()
                .taskId(task.getTaskId())
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .projectId(task.getProject().getProjectId())
                .accountId(task.getAccountId()).build();
        if (!Objects.isNull(task.getMilestone())) {
            taskResponse.builder()
                    .milestoneId(task.getMilestone().getMilestoneId()).build();
        }
        return taskResponse;
    }
}
