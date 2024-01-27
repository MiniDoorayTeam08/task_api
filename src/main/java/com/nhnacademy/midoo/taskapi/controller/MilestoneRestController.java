package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.MilestoneResponse;
import com.nhnacademy.midoo.taskapi.exception.ValidationFailedException;
import com.nhnacademy.midoo.taskapi.service.MilestoneService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/milestones", produces = MediaType.APPLICATION_JSON_VALUE)
public class MilestoneRestController {
    private final MilestoneService milestoneService;

    public MilestoneRestController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/{projectId}")
    public List<MilestoneResponse> getMilestone(@PathVariable Long projectId) {
        return milestoneService.getMilestone(projectId);
    }

    @PostMapping("/{projectId}/register")
    public MilestoneResponse createMilestone(@PathVariable Long projectId, @Valid @RequestBody MilestoneRequest request,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return milestoneService.createMilestone(projectId, request);
    }

    @PutMapping("/{milestoneId}")
    public MilestoneResponse modifyMilestone(@PathVariable Long milestoneId,
                                             @Valid @RequestBody MilestoneRequest request,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return milestoneService.modifyMilestone(milestoneId, request);
    }

    @DeleteMapping("/{milestoneId}")
    public void deleteMilestone(@PathVariable Long milestoneId) {
        milestoneService.deleteMilestone(milestoneId);
    }

}
