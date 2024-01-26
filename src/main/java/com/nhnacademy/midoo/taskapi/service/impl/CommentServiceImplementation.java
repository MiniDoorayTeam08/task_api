package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.CommentRequest;
import com.nhnacademy.midoo.taskapi.domain.CommentResponse;
import com.nhnacademy.midoo.taskapi.entity.Comment;
import com.nhnacademy.midoo.taskapi.exception.CommentNotExistException;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TaskNotExistException;
import com.nhnacademy.midoo.taskapi.repository.CommentRepository;
import com.nhnacademy.midoo.taskapi.repository.TaskRepository;
import com.nhnacademy.midoo.taskapi.service.CommentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public CommentResponse getComment(Long commentId) {
        return CommentResponse.fromEntity(
                commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long taskId) {
        List<Comment> comments = commentRepository.findByTask_TaskId(taskId);
        if (comments.isEmpty()) {
            throw new CommentNotExistException();
        }

        return comments.stream().map(CommentResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentResponse createComment(Long taskId, CommentRequest commentRequest) {
        Comment comment = CommentRequest.toEntity(commentRequest)
                .toBuilder().task(taskRepository.findById(taskId)
                        .orElseThrow(TaskNotExistException::new)).build();
        return CommentResponse.fromEntity(commentRepository.save(comment));
    }

    // TODO : modify는 나중에 수정 더 하기! => 어떤 것만 수정할지 정해야할 것 같음..
    @Override
    @Transactional
    public CommentResponse modifyComment(Long commentId, CommentRequest commentRequest) {
        Optional<Comment> changeComment = commentRepository.findById(commentId);

        if (changeComment.isEmpty()) {
            throw new ProjectNotExistException();
        }

        Comment comment = CommentRequest.toEntity(commentRequest);
        Comment resultComment = changeComment.get().toBuilder()
                .commentContent(comment.getCommentContent())
                .build();

        commentRepository.save(resultComment);
        return CommentResponse.fromEntity(resultComment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new CommentNotExistException();
        }
        commentRepository.deleteById(commentId);
    }
}
