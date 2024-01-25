package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.SetTagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import com.nhnacademy.midoo.taskapi.service.TagService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

// TODO : REST API 구현해주세요
@RestController
public class TagRestController {
    private final TagService tagService;

    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }
    @GetMapping("/{projectId} ")
    public List<Tag> getTags(@PathVariable Long projectId){
        return tagService.getTags(projectId);
    }
    @PostMapping("/{projectId}/register")
    public Tag createTag(@PathVariable Long projectId, @RequestBody TagRequest request){
        return tagService.createTag(projectId, request);
    }
    @PutMapping("/{tagId}")
    public Tag setTag(@PathVariable Long tagId, @RequestBody SetTagRequest request){
        return tagService.setTag(tagId, request);
    }

    @DeleteMapping("/{tagId}")
    public String deleteTag(@PathVariable Long tagId){
        return tagService.deleteTag(tagId);
    }
}
