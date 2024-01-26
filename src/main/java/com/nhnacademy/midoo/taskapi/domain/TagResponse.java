package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@Getter
public class TagResponse {
    Long tagId;
    String tagName;
    Long projectId;

    public static TagResponse fromEntity(Tag tag){
        return TagResponse.builder()
                .tagId(tag.getTagId())
                .tagName(tag.getTagName())
                .projectId(tag.getProject().getProjectId())
                .build();
    }
}
