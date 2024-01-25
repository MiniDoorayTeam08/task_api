package com.nhnacademy.midoo.taskapi.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tesk_tag")
public class TaskTag {
    @EmbeddedId
    private Pk pk;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable{
        @Column(name = "task_id")
        private Integer taskId;

        @Column(name = "tag_id")
        private Integer tagId;
    }

}
