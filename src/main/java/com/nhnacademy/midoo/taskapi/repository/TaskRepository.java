package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.domain.TaskDto;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<TaskDto> findByTaskId(Long taskId);

    List<Task> findByProject_ProjectId(Long projectId);
}
