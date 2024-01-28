package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {
    Long projectId;
    String accountId;
    String projectName;
    String projectExplain;
    String projectStatus;

    public static ProjectResponse fromEntity(Project project) {
        return ProjectResponse.builder()
                .projectId(project.getProjectId())
                .accountId(project.getAccountId())
                .projectName(project.getProjectName())
                .projectExplain(project.getProjectExplain())
                .projectStatus(project.getProjectStatus())
                .build();
    }
}
