package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.MilestoneResponse;
import java.util.List;


public interface MilestoneService {
    List<MilestoneResponse> getMilestone(Long projectId);

    void deleteMilestone(Long milestoneId);

    MilestoneResponse createMilestone(Long projectId, MilestoneRequest request);

    MilestoneResponse modifyMilestone(Long milestoneId, MilestoneRequest request);
}
