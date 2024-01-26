package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.SetTagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.midoo.taskapi.exception.TagAlreadyExistException;
import com.nhnacademy.midoo.taskapi.exception.TagNotExistException;
import com.nhnacademy.midoo.taskapi.repository.ProjectRepository;
import com.nhnacademy.midoo.taskapi.repository.TagRepository;
import com.nhnacademy.midoo.taskapi.service.TagService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    public final TagRepository tagRepository;
    public final ProjectRepository projectRepository;

    public TagServiceImpl(TagRepository tagRepository, ProjectRepository projectRepository) {
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Tag> getTags(Long projectId) {
        boolean isExist = projectRepository.existsById(projectId);
        if(!isExist) throw new ProjectNotFoundException();

        return tagRepository.findAllByProjectProjectId(projectId);
    }

    @Override
    public Tag createTag(Long projectId, TagRequest request) {
        boolean projectIdIsExist = projectRepository.existsById(projectId);
        if(!projectIdIsExist) throw new ProjectNotFoundException();
        boolean tagIsExist = tagRepository.findByProjectProjectIdAndTagName(projectId, request.getName()).isEmpty();
        if(!tagIsExist) throw new TagAlreadyExistException();


        Project project = projectRepository.getReferenceById(projectId);
        Tag newTag = new Tag();
        newTag.setTagName(request.getName());
        newTag.setProject(project);
        return tagRepository.save(newTag);
    }

    @Override
    public Tag modifyTag(Long tagId, SetTagRequest request) {
        boolean isExist = tagRepository.existsById(tagId);
        if(!isExist) throw new TagNotExistException();

        Tag tag =tagRepository.findById(tagId).orElseThrow(TagNotExistException::new);
        boolean isAlready = tagRepository.findByProjectProjectIdAndTagName(tag.getProject().getProjectId(), request.getName()).isEmpty();
        if(!isAlready) throw new TagAlreadyExistException();

        tag.setTagName(request.getName());
        return tagRepository.save(tag);
    }

    @Override
    public String deleteTag(Long tagId) {
        boolean isExist = tagRepository.existsById(tagId);
        if(!isExist) throw new TagNotExistException();
        tagRepository.deleteById(tagId);
        return "tag " + tagId + " : is deleted";
    }
}
