package com.nhnacademy.midoo.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.entity.Task;
import com.nhnacademy.midoo.taskapi.entity.TaskTag;
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
class TaskTagRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskTagRepository taskTagRepository;

    private Project project;

    private Task task;

    private Tag tag;

    private TaskTag taskTag;

    @BeforeEach
    void setUp(){
        project = new Project().toBuilder()
                .accountId("suyeon")
                .projectName("프로젝트 이름")
                .projectExplain("프로젝트 설명")
                .projectStatus("활동")
                .build();
        entityManager.persist(project);

        tag = new Tag().toBuilder()
                .tagName("태그 이름")
                .project(project)
                .build();

        entityManager.persist(tag);

        task = new Task().toBuilder()
                .taskTitle("업무 제목")
                .taskContent("업무 내용")
                .project(project)
                .accountId("test")
                .build();

        entityManager.persist(task);

        taskTag = new TaskTag().toBuilder()
                .pk(new TaskTag.Pk(task.getTaskId(), tag.getTagId()))
                .task(task)
                .tag(tag)
                .build();

        entityManager.persist(taskTag);
    }

    @Test
    @DisplayName("TaskId로 찾기")
    void findByTask_TaskId() {
        List<TaskTag> taskTags = taskTagRepository.findByTask_TaskId(task.getTaskId());

        assertThat(taskTags).hasSize(1);
    }

    @Test
    @DisplayName("taskId가 같은 tasktag 삭제")
    void deleteByPk_TaskId() {
        taskTagRepository.deleteByPk_TaskId(task.getTaskId());

        List<TaskTag> taskTags = taskTagRepository.findByTask_TaskId(task.getTaskId());

        assertThat(taskTags).hasSize(0);
    }
}