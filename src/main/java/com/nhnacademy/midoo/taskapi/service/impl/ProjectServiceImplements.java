package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.ProjectRequest;
import com.nhnacademy.midoo.taskapi.domain.ProjectResponse;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.ProjectMember;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.repository.ProjectMemberRepository;
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
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    @Transactional(readOnly = true)
    public ProjectResponse getProject(Long id) {
        return ProjectResponse.fromEntity(projectRepository.findById(id).orElseThrow(ProjectNotExistException::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjects(String accountId) {
        List<ProjectMember> projectMembers = projectMemberRepository.findByPk_AccountId(accountId);
        List<Project> projects = projectMembers.stream().map(ProjectMember::getProject).collect(Collectors.toList());
        return projects.stream().map(ProjectResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectAdmin(String accountId) {
        List<Project> projects = projectRepository.findByAccountId(accountId);
        return projects.stream().map(ProjectResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Project project = projectRepository.save(ProjectRequest.toEntity(projectRequest));
        projectRequest.getProjectMemberIdList().add(projectRequest.getAccountId());
        projectRequest.getProjectMemberIdList().forEach(
                memberId -> projectMemberRepository.save(
                        ProjectMember.builder()
                                .pk(new ProjectMember.Pk(memberId, project.getProjectId()))
                                .project(project)
                                .build()
                )
        );

        return ProjectResponse.fromEntity(project);
    }

    // TODO : modify는 나중에 수정 더 하기! => 어떤 것만 수정할지 정해야할 것 같음..
    @Override
    @Transactional
    public ProjectResponse modifyProject(Long id, ProjectRequest projectRequest) {
        Optional<Project> changeProject = projectRepository.findById(id);

        if(changeProject.isEmpty()){
            throw new ProjectNotExistException();
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
            throw new ProjectNotExistException();
        }
        projectRepository.deleteById(id);
    }
}
