package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.*;
import com.nhnacademy.midoo.taskapi.exception.ValidationFailedException;
import com.nhnacademy.midoo.taskapi.service.TaskService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping("/{projectId}")
    public List<TaskResponse> getTasks(@PathVariable Long projectId) {
        return taskService.getTasks(projectId);
    }

    @GetMapping("/tags/{taskId}")
    public List<TagResponse> getTagList(@PathVariable Long taskId) {
        return taskService.getTagList(taskId);
    }

    @GetMapping("/milestone/{taskId}")
    public MilestoneResponse getMilestone(@PathVariable Long taskId) {
        return taskService.getMilestone(taskId);
    }

    @GetMapping("/{taskId}/detail")
    public TaskDetailResponse getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping("/{taskId}/taskDto")
    public TaskDto getTaskDto(@PathVariable("taskId") Long taskId) {
        return taskService.getTaskDto(taskId);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse registerTask(@Valid @RequestBody TaskRequest taskRequest,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/{taskId}")
    public TaskResponse modifyTask(@PathVariable Long taskId,
                                   @Valid @RequestBody TaskRequest taskRequest,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return taskService.modifyTask(taskId, taskRequest);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
