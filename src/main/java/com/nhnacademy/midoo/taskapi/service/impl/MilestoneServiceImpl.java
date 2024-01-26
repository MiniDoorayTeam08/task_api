package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.SetMilestoneRequest;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.exception.MilestoneNotExistException;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.repository.MilestoneRepository;
import com.nhnacademy.midoo.taskapi.repository.ProjectRepository;
import com.nhnacademy.midoo.taskapi.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<Milestone> getMilestone(Long projectId) {
        //TODO : project 예외처리


        return milestoneRepository.findAllByProjectProjectId(projectId);
    }

    @Override
    public Milestone createMilestone(Long projectId, MilestoneRequest request) {
        //TODO : project 넣기, 예외처리

        Milestone newMilestone = new Milestone();
        newMilestone.setMilestoneName(request.getName());
        newMilestone.setProject(projectRepository.findById(projectId).orElseThrow(ProjectNotExistException::new));
//        newMilestone.setProject();
        return milestoneRepository.save(newMilestone);
    }

    @Override
    public Milestone setMilestone(Long milestoneId, SetMilestoneRequest request) {
        boolean isExist = milestoneRepository.existsById(milestoneId);
        if(!isExist) throw new MilestoneNotExistException();

        Milestone newMilestone = new Milestone();
        newMilestone.setMilestoneName(request.getName());
        return milestoneRepository.save(newMilestone);
    }


    @Override
    public String deleteMilestone(Long milestoneId) {
        boolean isExist = milestoneRepository.existsById(milestoneId);
        if(!isExist) throw new MilestoneNotExistException();
        milestoneRepository.deleteById(milestoneId);
        return "milestone " + milestoneId + " : is deleted";
    }


}
