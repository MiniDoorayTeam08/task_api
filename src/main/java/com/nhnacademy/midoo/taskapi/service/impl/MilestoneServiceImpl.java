package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.SetMilestoneRequest;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.exception.MilestoneNotExistException;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.repository.MilestoneRepository;
import com.nhnacademy.midoo.taskapi.repository.ProjectRepository;
import com.nhnacademy.midoo.taskapi.service.MilestoneService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Milestone> getMilestone(Long projectId) {
        boolean isExist = projectRepository.existsById(projectId);
        if (!isExist) {
            throw new ProjectNotExistException();
        }

        return milestoneRepository.findAllByProjectProjectId(projectId);
    }

    @Override
    public Milestone createMilestone(Long projectId, MilestoneRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotExistException::new);
        Milestone newMilestone = new Milestone();
        newMilestone.setMilestoneName(request.getName());
        newMilestone.setProject(project);
        return milestoneRepository.save(newMilestone);
    }

    @Override
    public Milestone modifyMilestone(Long milestoneId, SetMilestoneRequest request) {
        boolean isExist = milestoneRepository.existsById(milestoneId);
        if (!isExist) {
            throw new MilestoneNotExistException();
        }

        Milestone newMilestone = new Milestone();
        newMilestone.setMilestoneName(request.getName());
        return milestoneRepository.save(newMilestone);
    }


    @Override
    public String deleteMilestone(Long milestoneId) {
        boolean isExist = milestoneRepository.existsById(milestoneId);
        if (!isExist) {
            throw new MilestoneNotExistException();
        }
        milestoneRepository.deleteById(milestoneId);
        return "milestone " + milestoneId + " : is deleted";
    }


}
