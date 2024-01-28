package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.domain.MilestoneDto;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findAllByProjectProjectId(Long projectId);

    Optional<MilestoneDto> findByMilestoneId(Long id);
}
