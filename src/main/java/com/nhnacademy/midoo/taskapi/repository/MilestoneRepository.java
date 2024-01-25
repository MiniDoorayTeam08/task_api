package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.Milestone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO : 1/25
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findAllByProjectProjectId(Long projectId);
}
