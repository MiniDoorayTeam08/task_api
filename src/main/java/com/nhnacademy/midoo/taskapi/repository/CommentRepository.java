package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.domain.CommentDto;
import com.nhnacademy.midoo.taskapi.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentDto> findAllByTask_TaskId(Long taskId);
    List<Comment> findByTask_TaskId(Long taskId);
}
