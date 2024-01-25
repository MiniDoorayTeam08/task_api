package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// TODO : 1/25
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByProjectProjectId(Long projectId);

}
