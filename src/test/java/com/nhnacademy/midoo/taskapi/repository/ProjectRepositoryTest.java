package com.nhnacademy.midoo.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.midoo.taskapi.entity.Project;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    @DisplayName("계정 ID로 프로젝트 조회")
    void findByAccountId() {
        Project project = new Project().toBuilder()
                .accountId("suyeon")
                .projectName("프로젝트 이름")
                .projectExplain("프로젝트 설명")
                .projectStatus("활동")
                .build();
        this.testEntityManager.persist(project);

        List<Project> byAccountId = projectRepository.findByAccountId("suyeon");
        assertThat(byAccountId).hasSize(1);
    }
}