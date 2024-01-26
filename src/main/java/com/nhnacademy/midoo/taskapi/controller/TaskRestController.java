package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.MilestoneResponse;
import com.nhnacademy.midoo.taskapi.domain.TagResponse;
import com.nhnacademy.midoo.taskapi.domain.TaskRequest;
import com.nhnacademy.midoo.taskapi.domain.TaskResponse;
import com.nhnacademy.midoo.taskapi.exception.ValidationFailedException;
import com.nhnacademy.midoo.taskapi.service.TaskService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
