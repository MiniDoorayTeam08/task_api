package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilestoneRequest {
    @NotBlank
    String milestoneName;

    public static Milestone toEntity(MilestoneRequest milestoneRequest, Project project) {
        return Milestone.builder()
                .milestoneName(milestoneRequest.milestoneName)
                .project(project).build();
    }
}
