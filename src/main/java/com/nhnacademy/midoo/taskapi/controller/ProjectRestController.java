package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.ProjectRequest;
import com.nhnacademy.midoo.taskapi.domain.ProjectResponse;
import com.nhnacademy.midoo.taskapi.exception.ValidationFailedException;
import com.nhnacademy.midoo.taskapi.service.ProjectService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectRestController {
    private final ProjectService projectService;

    @GetMapping("/{projectId}")
    public ProjectResponse getProject(@PathVariable Long projectId) {
        return projectService.getProject(projectId);
    }

    @GetMapping("/myprojects/{accountId}")
    public List<ProjectResponse> getProjects(@PathVariable String accountId) {
        return projectService.getProjects(accountId);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse registerProject(@Valid @RequestBody ProjectRequest projectRequest,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return projectService.createProject(projectRequest);
    }

    @PutMapping("/{projectId}")
    public ProjectResponse modifyProject(@PathVariable Long projectId,
                                         @Valid @RequestBody ProjectRequest projectRequest,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return projectService.modifyProject(projectId, projectRequest);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }
}
