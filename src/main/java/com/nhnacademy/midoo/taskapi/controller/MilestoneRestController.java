package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.SetMilestoneRequest;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.service.MilestoneService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/milestones")
public class MilestoneRestController {
    private final MilestoneService milestoneService;

    public MilestoneRestController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/{projectId}")
    public List<Milestone> getMilestone(@PathVariable Long projectId){
        return milestoneService.getMilestone(projectId);
    }
    @PostMapping("/{projectId}/register")
    public Milestone createMilestone(@PathVariable Long projectId, @RequestBody MilestoneRequest request){
        return milestoneService.createMilestone(projectId, request);
    }
    @PutMapping("/{milestoneId}")
    public Milestone setMilestone(@PathVariable Long milestoneId, @RequestBody SetMilestoneRequest request){
        return milestoneService.setMilestone(milestoneId, request);
    }
    @DeleteMapping("/{milestoneId}")
    public String deleteMilestone(@PathVariable Long milestoneId){
        return milestoneService.deleteMilestone(milestoneId);
    }

}
