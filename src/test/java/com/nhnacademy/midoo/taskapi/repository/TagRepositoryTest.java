package com.nhnacademy.midoo.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.midoo.taskapi.domain.TagDto;
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
class TagRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TagRepository tagRepository;

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
    @DisplayName("ProjectId로 찾기")
    void findAllByProjectProjectId() {
        List<Tag> tagList = tagRepository.findAllByProjectProjectId(project.getProjectId());

        assertThat(tagList).hasSize(1);
    }

    @Test
    @DisplayName("TagId로 찾기")
    void findByTagId() {
        TagDto byId = tagRepository.findByTagId(tag.getTagId()).orElse(null);

        assertThat(byId.getTagId()).isEqualTo(tag.getTagId());
        assertThat(byId.getTagName()).isEqualTo(tag.getTagName());
    }

    @Test
    @DisplayName("TaskId로 찾기")
    void findAllBy() {
        List<TagDto> tagDtoList = tagRepository.findAllBy(task.getTaskId());

        assertThat(tagDtoList).hasSize(1);
    }
}