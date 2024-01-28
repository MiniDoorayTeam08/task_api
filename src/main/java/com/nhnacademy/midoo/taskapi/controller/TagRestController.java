package com.nhnacademy.midoo.taskapi.controller;

import com.nhnacademy.midoo.taskapi.domain.TagDto;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagResponse;
import com.nhnacademy.midoo.taskapi.service.TagService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagRestController {
    private final TagService tagService;

    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{projectId}")
    public List<TagResponse> getTags(@PathVariable Long projectId) {
        return tagService.getTags(projectId);
    }

    @GetMapping("/{tagId}/tagId")
    public TagDto getTag(@PathVariable Long tagId) {
        return tagService.getTag(tagId);
    }

    @PostMapping("/{projectId}/register")
    public TagResponse createTag(@PathVariable Long projectId, @RequestBody TagRequest request) {
        return tagService.createTag(projectId, request);
    }

    @PutMapping("/{tagId}")
    public TagResponse modifyTag(@PathVariable Long tagId, @RequestBody TagRequest request) {
        return tagService.modifyTag(tagId, request);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
    }
}
