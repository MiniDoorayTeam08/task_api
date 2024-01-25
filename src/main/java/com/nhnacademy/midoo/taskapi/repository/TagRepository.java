package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO : 1/25
public interface TagRepository extends JpaRepository<Tag, Long> {
}
