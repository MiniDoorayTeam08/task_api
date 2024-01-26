package com.nhnacademy.midoo.taskapi.service.impl;

import com.nhnacademy.midoo.taskapi.domain.SetTagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.exception.ProjectNotExistException;
import com.nhnacademy.midoo.taskapi.exception.TagNotExistException;
import com.nhnacademy.midoo.taskapi.repository.ProjectRepository;
import com.nhnacademy.midoo.taskapi.repository.TagRepository;
import com.nhnacademy.midoo.taskapi.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<Tag> getTags(Long projectId) {
        //TODO project 예외처리

        return tagRepository.findAllByProjectProjectId(projectId);
    }

    @Override
    public Tag createTag(Long projectId, TagRequest request) {
        //TODO : project 넣기, 예외처리

        Tag newTag = new Tag();
        newTag.setTagName(request.getName());
        newTag.setProject(projectRepository.findById(projectId).orElseThrow(ProjectNotExistException::new));
//        newTag.setProject(new Project());
        return tagRepository.save(newTag);
    }

    @Override
    public Tag setTag(Long tagId, SetTagRequest request) {
        boolean isExist = tagRepository.existsById(tagId);
        if(!isExist) throw new TagNotExistException();

        Tag tag =tagRepository.findById(tagId).orElseThrow(TagNotExistException::new);
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
