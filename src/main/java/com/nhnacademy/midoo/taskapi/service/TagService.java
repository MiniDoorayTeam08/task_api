package com.nhnacademy.midoo.taskapi.service;

import com.nhnacademy.midoo.taskapi.domain.SetTagRequest;
import com.nhnacademy.midoo.taskapi.domain.TagRequest;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import java.util.List;

// TODO : 1/25 = Impl까지 구현해주세요!
public interface TagService {
    List<Tag> getTags(Long projectId);

    Tag createTag(Long projectId, TagRequest request);

    Tag setTag(Long tagId, SetTagRequest request);

    String deleteTag(Long tagId);
}
