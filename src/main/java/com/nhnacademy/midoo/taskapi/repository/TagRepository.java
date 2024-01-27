package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.domain.TagDto;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByProjectProjectId(Long projectId);
    Optional<TagDto> findByTagId(Long tagId);

    Optional<Tag> findByProjectProjectIdAndTagName(Long projectId, String name);
    
}
