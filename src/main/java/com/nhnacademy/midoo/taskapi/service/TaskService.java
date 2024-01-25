package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.TaskRequest;
import com.nhnacademy.midoo.taskapi.domain.TaskResponse;
import java.util.List;

public interface TaskService {
    List<TaskResponse> getTasks(Long projectId);

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse modifyTask(Long taskId, TaskRequest taskRequest);

    void deleteTask(Long taskId);
}
