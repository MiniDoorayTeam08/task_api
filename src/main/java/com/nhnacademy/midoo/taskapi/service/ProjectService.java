package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.ProjectRequest;
import com.nhnacademy.midoo.taskapi.domain.ProjectResponse;
import java.util.List;

public interface ProjectService {
    ProjectResponse getProject(Long id);

    List<ProjectResponse> getProjects(String accountId);

    List<ProjectResponse> getProjectAdmin(String accountId);

    ProjectResponse createProject(ProjectRequest projectRequest);

    ProjectResponse modifyProject(Long id, ProjectRequest projectRequest);

    void deleteProject(Long id);
}
