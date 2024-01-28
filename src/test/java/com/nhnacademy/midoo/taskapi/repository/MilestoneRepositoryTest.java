package com.nhnacademy.midoo.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.midoo.taskapi.domain.MilestoneDto;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MilestoneRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    MilestoneRepository milestoneRepository;

    private Project project;
    private Milestone milestone;

    @BeforeEach
    void setUp(){
        project = new Project().toBuilder()
                .accountId("suyeon")
                .projectName("프로젝트 이름")
                .projectExplain("프로젝트 설명")
                .projectStatus("활동")
                .build();
        entityManager.persist(project);
        milestone = new Milestone().toBuilder()
                .milestoneName("실행")
                .project(project)
                .build();

        entityManager.persist(milestone);
    }

    @Test
    void findAllByProjectProjectId() {
        List<Milestone> byProjectId = milestoneRepository.findAllByProjectProjectId(project.getProjectId());
        assertThat(byProjectId).hasSize(1);
    }

    @Test
    void findByMilestoneId() {
        MilestoneDto byId = milestoneRepository.findByMilestoneId(milestone.getMilestoneId()).orElse(null);

        assertThat(byId.getMilestoneId()).isEqualTo(milestone.getMilestoneId());
        assertThat(byId.getMilestoneName()).isEqualTo(milestone.getMilestoneName());
    }
}