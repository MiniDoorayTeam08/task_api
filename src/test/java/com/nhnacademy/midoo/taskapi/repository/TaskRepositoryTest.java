package com.nhnacademy.midoo.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.midoo.taskapi.domain.TaskDto;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
class TaskRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskRepository taskRepository;

    private Project project;
    private Task task;

    @BeforeEach
    void setUp(){
        project = new Project().toBuilder()
                .accountId("suyeon")
                .projectName("프로젝트 이름")
                .projectExplain("프로젝트 설명")
                .projectStatus("활동")
                .build();
        entityManager.persist(project);

        task = new Task().toBuilder()
                .taskTitle("업무 제목")
                .taskContent("업무 내용")
                .project(project)
                .accountId("test")
                .build();

        entityManager.persist(task);
    }


    @Test
    @DisplayName("TaskId로 Task 찾기")
    void findByTaskId() {
        TaskDto taskDto = taskRepository.findByTaskId(task.getTaskId()).orElse(null);

        assertThat(taskDto.getTaskId()).isEqualTo(task.getTaskId());
        assertThat(taskDto.getTaskTitle()).isEqualTo(task.getTaskTitle());
        assertThat(taskDto.getTaskContent()).isEqualTo(task.getTaskContent());
        assertThat(taskDto.getMilestone()).isEqualTo(task.getMilestone());
    }

    @Test
    @DisplayName("ProjectId로 Task 찾기")
    void findByProject_ProjectId() {
        List<Task> tasks = taskRepository.findByProject_ProjectId(project.getProjectId());

        assertThat(tasks).hasSize(1);
    }
}