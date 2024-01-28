package com.nhnacademy.midoo.taskapi.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.midoo.taskapi.domain.ProjectResponse;
import com.nhnacademy.midoo.taskapi.service.ProjectService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = {ProjectRestController.class})
class ProjectRestControllerTest {
    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getProject() throws Exception {
        ProjectResponse projectResponse = new ProjectResponse(
                1L,
                "test",
                "projectName",
                "projectExplain",
                "활동");
        when(projectService.getProject(anyLong()))
                .thenReturn(projectResponse);

        mockMvc.perform(get("/projects/{projectId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value(projectResponse.getProjectId()))
                .andExpect(jsonPath("$.accountId").value(projectResponse.getAccountId()))
                .andExpect(jsonPath("$.projectName").value(projectResponse.getProjectName()))
                .andExpect(jsonPath("$.projectExplain").value(projectResponse.getProjectExplain()))
                .andExpect(jsonPath("$.projectStatus").value(projectResponse.getProjectStatus()));
    }

    @Test
    void getProjects() throws Exception {
        List<ProjectResponse> projects = List.of(
                new ProjectResponse(1L, "user123", "Project 1", "Explain 1", "Active"),
                new ProjectResponse(2L, "user123", "Project 2", "Explain 2", "Inactive")
        );
        when(projectService.getProjects(anyString())).thenReturn(projects);

        ResultActions resultActions = mockMvc.perform(get("/projects/myprojects/{accountId}", "user123")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].projectId").value(1))
                .andExpect(jsonPath("$[0].accountId").value("user123"))
                .andExpect(jsonPath("$[0].projectName").value("Project 1"))
                .andExpect(jsonPath("$[0].projectExplain").value("Explain 1"))
                .andExpect(jsonPath("$[0].projectStatus").value("Active"))
                .andExpect(jsonPath("$[1].projectId").value(2))
                .andExpect(jsonPath("$[1].accountId").value("user123"))
                .andExpect(jsonPath("$[1].projectName").value("Project 2"))
                .andExpect(jsonPath("$[1].projectExplain").value("Explain 2"))
                .andExpect(jsonPath("$[1].projectStatus").value("Inactive"));
    }
}