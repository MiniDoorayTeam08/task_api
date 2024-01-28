package com.nhnacademy.midoo.taskapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.midoo.taskapi.entity.Comment;
import com.nhnacademy.midoo.taskapi.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CommentRepository commentRepository;

    @Test
    void testFindByTaskId() {

//        Task task = new Task("task1", "taskContent1", "project", )
//        Comment comment = new Comment(1L, "테스트입니다", task, "jpaTest")
    }

    @Test
    void findAllByTask_TaskId() {
    }

    @Test
    void findByTask_TaskId() {
    }
}