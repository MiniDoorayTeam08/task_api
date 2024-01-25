package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO : 1/25
public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
