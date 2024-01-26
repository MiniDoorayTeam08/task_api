package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.SetTagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import java.util.List;

public interface TagService {
    List<Tag> getTags(Long projectId);

    Tag createTag(Long projectId, TagRequest request);

    Tag modifyTag(Long tagId, SetTagRequest request);

    String deleteTag(Long tagId);
}
