package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.MilestoneResponse;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.exception.MilestoneNotExistException;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.repository.MilestoneRepository;
import com.nhnacademy.midoo.taskapi.repository.ProjectRepository;
import com.nhnacademy.midoo.taskapi.service.MilestoneService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<MilestoneResponse> getMilestone(Long projectId) {
        boolean isExist = projectRepository.existsById(projectId);
        if (!isExist) {
            throw new ProjectNotExistException();
        }

        return milestoneRepository.findAllByProjectProjectId(projectId)
                .stream().map(MilestoneResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MilestoneResponse createMilestone(Long projectId, MilestoneRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotExistException::new);

        return MilestoneResponse.fromEntity(milestoneRepository.save(MilestoneRequest.toEntity(request, project)));
    }

    @Override
    @Transactional
    public MilestoneResponse modifyMilestone(Long milestoneId, MilestoneRequest request) {
        Milestone changeMilestone = milestoneRepository.findById(milestoneId).orElseThrow(MilestoneNotExistException::new);

        Milestone milestone = MilestoneRequest.toEntity(request, changeMilestone.getProject());
        Milestone resultMilestone = changeMilestone.toBuilder().milestoneName(milestone.getMilestoneName()).build();

        milestoneRepository.save(resultMilestone);
        return MilestoneResponse.fromEntity(resultMilestone);
    }


    @Override
    @Transactional
    public void deleteMilestone(Long milestoneId) {
        boolean isExist = milestoneRepository.existsById(milestoneId);
        if (!isExist) {
            throw new MilestoneNotExistException();
        }
        milestoneRepository.deleteById(milestoneId);
    }


}
