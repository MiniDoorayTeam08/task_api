package com.nhnacademy.midoo.taskapi.repository;

import com.nhnacademy.midoo.taskapi.entity.ProjectMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
    List<ProjectMember> findByPk_AccountId(String accountId);
}