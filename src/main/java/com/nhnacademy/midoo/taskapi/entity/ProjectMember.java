package com.nhnacademy.midoo.taskapi.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "project_member")
public class ProjectMember {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "account_id")
        String accountId;

        @Column(name = "project_id")
        Long projectId;
    }
}
