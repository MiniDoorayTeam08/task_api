package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.TagDto;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagResponse;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TagNotExistException;
import com.nhnacademy.midoo.taskapi.repository.ProjectRepository;
import com.nhnacademy.midoo.taskapi.repository.TagRepository;
import com.nhnacademy.midoo.taskapi.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl implements TagService {
    public final TagRepository tagRepository;
    public final ProjectRepository projectRepository;

    public TagServiceImpl(TagRepository tagRepository, ProjectRepository projectRepository) {
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<TagResponse> getTags(Long projectId) {
        boolean isExist = projectRepository.existsById(projectId);
        if (!isExist) {
            throw new ProjectNotExistException();
        }

        return tagRepository.findAllByProjectProjectId(projectId).stream()
                .map(TagResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TagResponse createTag(Long projectId, TagRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotExistException::new);

        return TagResponse.fromEntity(tagRepository.save(TagRequest.toEntity(request, project)));
    }

    @Override
    @Transactional
    public TagResponse modifyTag(Long tagId, TagRequest request) {
        Tag changeTag = tagRepository.findById(tagId).orElseThrow(TagNotExistException::new);

        Tag tag = TagRequest.toEntity(request, changeTag.getProject());
        Tag resultTag = changeTag.toBuilder().tagName(tag.getTagName()).build();
        tagRepository.save(resultTag);

        return TagResponse.fromEntity(resultTag);
    }

    @Override
    @Transactional
    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    @Override
    @Transactional(readOnly = true)
    public TagDto getTag(Long tagId) {
        return tagRepository.findByTagId(tagId).orElseThrow(TagNotExistException::new);
    }


}
