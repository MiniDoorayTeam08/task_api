package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.domain.TagDto;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByProjectProjectId(Long projectId);
    Optional<TagDto> findByTagId(Long tagId);

    @Query("SELECT t FROM Tag t " +
            "LEFT JOIN TaskTag tt " +
            "ON t.tagId = tt.pk.tagId " +
            "WHERE tt.pk.taskId = :taskId")
    List<TagDto> findAllBy(Long taskId);
}
