package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.*;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.List;

public interface TaskService {
    List<TaskResponse> getTasks(Long projectId);

    List<TagResponse> getTagList(Long projectId);

    MilestoneResponse getMilestone(Long taskId);

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse modifyTask(Long taskId, TaskRequest taskRequest);

    void deleteTask(Long taskId);

    TaskDetailResponse getTask(Long taskId);
}
