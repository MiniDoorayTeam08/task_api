package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.*;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Task;
import com.nhnacademy.midoo.taskapi.entity.TaskTag;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TaskNotExistException;
import com.nhnacademy.midoo.taskapi.repository.*;
import com.nhnacademy.midoo.taskapi.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImplementation implements TaskService {
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;
    private final TaskTagRepository taskTagRepository;
    private final CommentRepository commentRepository;

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
        Project project = projectRepository.findById(taskRequest.getProjectId()).orElseThrow(ProjectNotExistException::new);
        Milestone milestone = milestoneRepository.findById(taskRequest.getMilestoneId()).orElse(null);
        log.info("milestone : {}", milestone.getMilestoneId());
        Task task = TaskRequest.toEntity(taskRequest, project, milestone);
        Task resultTask = taskRepository.save(task);
        if(!taskRequest.getTagListId().isEmpty()){
            taskRequest.getTagListId().stream().map(
                    tagId -> tagRepository.findById(tagId).get()
            ).forEach(tag ->
                    taskTagRepository.save(TaskTag.builder()
                            .pk(new TaskTag.Pk(tag.getTagId(), task.getTaskId())).tag(tag).task(task).build()
                    )
            );
        }
        return TaskResponse.fromEntity(resultTask);
    }

    // TODO : modify는 나중에 수정 더 하기! => 어떤 것만 수정할지 정해야할 것 같음..
    @Override
    @Transactional
    public TaskResponse modifyTask(Long taskId, TaskRequest taskRequest) {
        Task changeTask = taskRepository.findById(taskId).orElseThrow(TaskNotExistException::new);

        Task task = TaskRequest.toEntity(taskRequest, changeTask.getProject(), changeTask.getMilestone());
        Task resultTask = changeTask.toBuilder()
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

    @Override
    @Transactional
    public TaskDetailResponse getTask(Long taskId) {
        TaskDto task = taskRepository.findByTaskId(taskId).orElseThrow(TaskNotExistException::new);
        List<CommentDto> comment = commentRepository.findAllByTask_TaskId(taskId);
        List<TagDto> tag = tagRepository.findAllBy(taskId);

        return new TaskDetailResponse(task, tag, comment);
    }

    @Override
    public TaskDto getTaskDto(Long taskId) {
        return taskRepository.findByTaskId(taskId).orElseThrow(TaskNotExistException::new);
    }
}
