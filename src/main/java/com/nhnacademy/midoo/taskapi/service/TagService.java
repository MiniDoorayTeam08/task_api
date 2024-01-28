package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.TagDto;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagResponse;
import java.util.List;

public interface TagService {
    List<TagResponse> getTags(Long projectId);

    TagResponse createTag(Long projectId, TagRequest request);

    TagResponse modifyTag(Long tagId, TagRequest request);

    void deleteTag(Long tagId);

    TagDto getTag(Long tagId);
}
