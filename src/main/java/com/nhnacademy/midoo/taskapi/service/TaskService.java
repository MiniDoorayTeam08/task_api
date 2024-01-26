package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.MilestoneResponse;
import com.nhnacademy.midoo.taskapi.domain.TagResponse;
import com.nhnacademy.midoo.taskapi.domain.TaskRequest;
import com.nhnacademy.midoo.taskapi.domain.TaskResponse;
import com.nhnacademy.midoo.taskapi.entity.Task;
import java.util.List;

public interface TaskService {
    List<TaskResponse> getTasks(Long projectId);

    List<TagResponse> getTagList(Long projectId);

    MilestoneResponse getMilestone(Long taskId);

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse modifyTask(Long taskId, TaskRequest taskRequest);

    void deleteTask(Long taskId);
}
