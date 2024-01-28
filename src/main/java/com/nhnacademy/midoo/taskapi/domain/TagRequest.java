package com.nhnacademy.midoo.taskapi.domain;

import com.nhnacademy.midoo.taskapi.entity.Project;
import com.nhnacademy.midoo.taskapi.entity.Tag;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagRequest {
    String tagName;

    public static Tag toEntity(TagRequest tagRequest, Project project) {
        return Tag.builder()
                .tagName(tagRequest.getTagName())
                .project(project).build();
    }
}
