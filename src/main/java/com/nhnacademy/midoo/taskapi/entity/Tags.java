package com.nhnacademy.midoo.taskapi.entity;

import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tags {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;

    @Column(name = "name")
    private String name;

    @Column(name = "project_id")
    private Integer projectId;

    @OneToMany(mappedBy = "tag")
    private List<TaskTags> taskTags;
}
