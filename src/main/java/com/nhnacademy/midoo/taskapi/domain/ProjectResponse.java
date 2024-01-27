package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Project;
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
public class ProjectResponse {
    @NotNull
    Long projectId;

    @NotBlank
    @Size(max = 50)
    String accountId;

    @NotBlank
    @Size(max = 10)
    String projectName;

    @NotBlank
    @Size(max = 100)
    String projectExplain;

    @Size(max = 300)
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
