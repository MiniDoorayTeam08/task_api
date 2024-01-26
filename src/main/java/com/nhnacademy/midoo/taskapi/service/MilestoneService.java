package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.SetMilestoneRequest;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import java.util.List;


public interface MilestoneService {
    List<Milestone> getMilestone(Long projectId);

    String deleteMilestone(Long milestoneId);

    Milestone createMilestone(Long projectId, MilestoneRequest request);

    Milestone setMilestone(Long milestoneId, SetMilestoneRequest request);
}
