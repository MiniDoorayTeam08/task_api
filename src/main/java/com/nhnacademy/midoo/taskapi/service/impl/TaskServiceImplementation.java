package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.TaskRequest;
import com.nhnacademy.midoo.taskapi.domain.TaskResponse;
import com.nhnacademy.midoo.taskapi.entity.Task;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.midoo.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.midoo.taskapi.repository.TaskRepository;
import com.nhnacademy.midoo.taskapi.service.TaskService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasks(Long projectId) {
        List<Task> tasks = taskRepository.findByProject_ProjectId(projectId);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return tasks.stream().map(TaskResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = TaskRequest.toEntity(taskRequest);
        return TaskResponse.fromEntity(taskRepository.save(task));
    }

    // TODO : modify용 taskRequest만들기
    @Override
    @Transactional
    public TaskResponse modifyTask(Long taskId, TaskRequest taskRequest) {
        Optional<Task> changeTask = taskRepository.findById(taskId);

        if (changeTask.isEmpty()) {
            throw new ProjectNotFoundException();
        }

        Task task = TaskRequest.toEntity(taskRequest);
        Task resultTask = changeTask.get().toBuilder()
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .build();

        taskRepository.save(resultTask);
        return TaskResponse.fromEntity(resultTask);
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(taskId);
    }
}
