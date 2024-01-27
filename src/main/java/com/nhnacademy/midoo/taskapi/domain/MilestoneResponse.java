package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class MilestoneResponse {
    @NotNull
    private Long milestoneId;

    @NotBlank
    private String milestoneName;

    @NotNull
    private Long projectId;

    public static MilestoneResponse fromEntity(Milestone milestone) {
        return MilestoneResponse.builder()
                .milestoneId(milestone.getMilestoneId())
                .milestoneName(milestone.getMilestoneName())
                .projectId(milestone.getProject().getProjectId())
                .build();
    }
}
