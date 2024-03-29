package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank
    @Size(max = 100) String taskTitle;

    @NotBlank String taskContent;

    @NotNull Long projectId;

    @Builder.Default
    Long milestoneId = 0L;

    @Builder.Default
    List<Long> tagListId = new ArrayList<>();

    @NotBlank
    @Size(max = 50) String accountId;

    public static Task toEntity(TaskRequest taskRequest, Project project, Milestone milestone) {
        return Task.builder()
                .taskTitle(taskRequest.getTaskTitle())
                .taskContent(taskRequest.getTaskContent())
                .project(project)
                .milestone(milestone)
                .accountId(taskRequest.getAccountId())
                .build();
    }
}
