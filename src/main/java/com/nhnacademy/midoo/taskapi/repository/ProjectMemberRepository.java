package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}
