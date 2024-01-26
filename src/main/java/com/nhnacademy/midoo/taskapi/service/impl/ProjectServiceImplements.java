package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.ProjectRequest;
import com.nhnacademy.midoo.taskapi.domain.ProjectResponse;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.midoo.taskapi.repository.ProjectRepository;
import com.nhnacademy.midoo.taskapi.service.ProjectService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImplements implements ProjectService {
    private final ProjectRepository projectRepository;
    @Override
    @Transactional(readOnly = true)
    public ProjectResponse getProject(Long id) {
        return ProjectResponse.fromEntity(projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjects(String accountId) {
        List<Project> projects = projectRepository.findByAccountId(accountId);
        if(projects.isEmpty()){
            throw new ProjectNotFoundException();
        }
        return projects.stream().map(ProjectResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Project project = ProjectRequest.toEntity(projectRequest);
        return ProjectResponse.fromEntity(projectRepository.save(project));
    }

    // TODO : modify용 projectRequest만들기
    @Override
    @Transactional
    public ProjectResponse modifyProject(Long id, ProjectRequest projectRequest) {
        Optional<Project> changeProject = projectRepository.findById(id);

        if(changeProject.isEmpty()){
            throw new ProjectNotFoundException();
        }

        Project project = ProjectRequest.toEntity(projectRequest);
        Project resultProject = changeProject.get().toBuilder()
                .projectName(project.getProjectName())
                .projectExplain(project.getProjectExplain())
                .projectStatus(project.getProjectStatus())
                .build();

        projectRepository.save(resultProject);
        return ProjectResponse.fromEntity(resultProject);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        if(!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException();
        }
        projectRepository.deleteById(id);
    }
}
