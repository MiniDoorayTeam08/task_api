package com.nhnacademy.midoo.taskapi.domain;

public interface TaskDto {
    Long getTaskId();

    String getTaskTitle();

    String getTaskContent();

    MilestoneDto getMilestone();
}
