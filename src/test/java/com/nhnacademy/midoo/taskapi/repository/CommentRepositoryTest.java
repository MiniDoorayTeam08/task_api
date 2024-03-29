package com.nhnacademy.midoo.taskapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.midoo.taskapi.domain.CommentDto;
import com.nhnacademy.midoo.taskapi.entity.Comment;
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
class CommentRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CommentRepository commentRepository;

    private Project project;

    private Task task;

    private Comment comment;

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

        comment = new Comment().toBuilder()
                .commentContent("댓글 내용")
                .task(task)
                .accountId("test")
                .build();

        entityManager.persist(comment);
    }

    @Test
    @DisplayName("TaskId로 댓글들 찾기")
    void testFindAllByTaskId() {
        List<CommentDto> byTaskId = commentRepository.findAllByTask_TaskId(task.getTaskId());

        assertThat(byTaskId).hasSize(1);
    }

    @Test
    @DisplayName("TaskId로 댓글들 찾기")
    void findByTaskId() {
        List<Comment> byTaskId = commentRepository.findByTask_TaskId(task.getTaskId());

        assertThat(byTaskId).hasSize(1);
    }

    @Test
    void deleteByTaskId() {
        commentRepository.deleteByTask_TaskId(task.getTaskId());

        List<Comment> byTaskId = commentRepository.findByTask_TaskId(task.getTaskId());
        assertThat(byTaskId).hasSize(0);
    }
}