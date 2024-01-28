package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.TaskTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
    List<TaskTag> findByTask_TaskId(Long taskId);

    void deleteByPk_TaskId(Long TaskId);
}
