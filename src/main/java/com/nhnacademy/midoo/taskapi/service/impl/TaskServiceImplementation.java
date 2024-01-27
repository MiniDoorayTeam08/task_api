package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.MilestoneResponse;
import com.nhnacademy.midoo.taskapi.domain.TagResponse;
import com.nhnacademy.midoo.taskapi.domain.TaskRequest;
import com.nhnacademy.midoo.taskapi.domain.TaskResponse;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.entity.Task;
import com.nhnacademy.midoo.taskapi.entity.TaskTag;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TaskNotExistException;
import com.nhnacademy.midoo.taskapi.repository.TaskRepository;
import com.nhnacademy.midoo.taskapi.repository.TaskTagRepository;
import com.nhnacademy.midoo.taskapi.service.TaskService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImplementation implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskTagRepository taskTagRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasks(Long projectId) {
        List<Task> tasks = taskRepository.findByProject_ProjectId(projectId);
        return tasks.stream().map(TaskResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagResponse> getTagList(Long taskId){
        List<TaskTag> tags = taskTagRepository.findByTask_TaskId(taskId);
        return tags.stream().map(taskTag -> TagResponse.fromEntity(taskTag.getTag())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MilestoneResponse getMilestone(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistException::new);
        return MilestoneResponse.fromEntity(task.getMilestone());
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = TaskRequest.toEntity(taskRequest);
        List<Tag> tagList = taskRequest.getTagList();
        Task resultTask = taskRepository.save(task);
        tagList.forEach(tag ->
                taskTagRepository.save(TaskTag.builder()
                        .pk(new TaskTag.Pk(tag.getTagId(), task.getTaskId())).tag(tag).task(task).build()
                )
        );
        return TaskResponse.fromEntity(resultTask);
    }

    // TODO : modify는 나중에 수정 더 하기! => 어떤 것만 수정할지 정해야할 것 같음..
    @Override
    @Transactional
    public TaskResponse modifyTask(Long taskId, TaskRequest taskRequest) {
        Optional<Task> changeTask = taskRepository.findById(taskId);

        if (changeTask.isEmpty()) {
            throw new ProjectNotExistException();
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
            throw new TaskNotExistException();
        }
        taskRepository.deleteById(taskId);
    }
}
