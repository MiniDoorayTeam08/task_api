package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.MilestoneRequest;
import com.nhnacademy.midoo.taskapi.domain.SetMilestoneRequest;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.exception.MilestoneNotExistException;
import com.nhnacademy.midoo.taskapi.repository.MilestoneRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MilestoneServiceImpl implements MilestoneService{
    private final MilestoneRepository milestoneRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

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
