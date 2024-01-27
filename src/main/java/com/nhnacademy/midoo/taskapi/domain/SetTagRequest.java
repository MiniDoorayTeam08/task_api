package com.nhnacademy.midoo.taskapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetTagRequest {
    String name;
}
