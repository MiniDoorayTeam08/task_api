package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilestoneResponse {
    private Long milestoneId;
    private String milestoneName;
    private Long projectId;

    public static MilestoneResponse fromEntity(Milestone milestone) {
        return MilestoneResponse.builder()
                .milestoneId(milestone.getMilestoneId())
                .milestoneName(milestone.getMilestoneName())
                .projectId(milestone.getProject().getProjectId())
                .build();
    }
}
