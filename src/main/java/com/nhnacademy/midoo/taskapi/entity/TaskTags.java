package com.nhnacademy.midoo.taskapi.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tesk_tag")
public class TaskTags {

    //TODO :: db
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tags tag;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks task;
}