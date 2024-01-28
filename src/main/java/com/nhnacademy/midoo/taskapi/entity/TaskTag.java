package com.nhnacademy.midoo.taskapi.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "task_tag")
public class TaskTag {
    @EmbeddedId
    private Pk pk;

    @MapsId("tagId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @MapsId("taskId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "tag_id")
        private Long tagId;

        @Column(name = "task_id")
        private Long taskId;
    }
}