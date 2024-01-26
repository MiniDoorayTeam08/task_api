package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
