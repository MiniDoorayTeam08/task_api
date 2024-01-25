package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.SetMilestoneRequest;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import java.util.List;

// TODO : 1/25 = Impl까지 구현해주세요!
public interface MilestoneService {
    List<Milestone> getMilestone(Long projectId);



    String deleteMilestone(Long milestoneId);

    Milestone createMilestone(Long projectId, MilestoneRequest request);

    Milestone setMilestone(Long milestoneId, SetMilestoneRequest request);
}
