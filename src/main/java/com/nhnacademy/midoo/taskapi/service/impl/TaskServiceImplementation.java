package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.*;
import com.nhnacademy.midoo.taskapi.entity.Milestone;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.entity.Task;
import com.nhnacademy.midoo.taskapi.entity.TaskTag;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TagNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TaskNotExistException;
import com.nhnacademy.midoo.taskapi.repository.*;
import com.nhnacademy.midoo.taskapi.service.TaskService;
import java.util.List;
import java.util.Objects;
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
        Task task = TaskRequest.toEntity(taskRequest, project, milestone);
        Task resultTask = taskRepository.save(task);
        createTaskTag(taskRequest, task);
        return TaskResponse.fromEntity(resultTask);
    }

    @Override
    @Transactional
    public TaskResponse modifyTask(Long taskId, TaskRequest taskRequest) {
        Task changeTask = taskRepository.findById(taskId).orElseThrow(TaskNotExistException::new);
        Milestone chageMilestone = milestoneRepository.findById(taskRequest.getMilestoneId()).orElse(null);
        Task task = TaskRequest.toEntity(taskRequest, changeTask.getProject(), changeTask.getMilestone());
        Task resultTask = changeTask.toBuilder()
                .taskTitle(task.getTaskTitle())
                .taskContent(task.getTaskContent())
                .milestone(chageMilestone)
                .build();

        taskRepository.save(resultTask);

        taskTagRepository.deleteByPk_TaskId(taskId);
        createTaskTag(taskRequest, changeTask);

        return TaskResponse.fromEntity(resultTask);
    }

    @Transactional
    public void createTaskTag(TaskRequest taskRequest, Task task){
        if(!Objects.isNull(taskRequest.getTagListId()) && !taskRequest.getTagListId().isEmpty()){
            List<Tag> tagList = taskRequest.getTagListId().stream().map(
                    tagId -> tagRepository.findById(tagId).orElseThrow(TagNotExistException::new)
            ).collect(Collectors.toList());
            tagList.stream().forEach(
                    tag -> taskTagRepository.save(new TaskTag().toBuilder().pk(new TaskTag.Pk(tag.getTagId(), task.getTaskId())).tag(tag).task(task).build())
            );
        }
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
    @Transactional(readOnly = true)
    public TaskDto getTaskDto(Long taskId) {
        return taskRepository.findByTaskId(taskId).orElseThrow(TaskNotExistException::new);
    }
}
