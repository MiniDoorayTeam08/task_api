package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Project;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
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
public class ProjectRequest {
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

    List<String> projectMemberIdList;

    public static Project toEntity(ProjectRequest projectRequest) {
        return Project.builder()
                .accountId(projectRequest.getAccountId())
                .projectName(projectRequest.getProjectName())
                .projectExplain(projectRequest.getProjectExplain())
                .projectStatus(projectRequest.getProjectStatus())
                .build();
    }
}
