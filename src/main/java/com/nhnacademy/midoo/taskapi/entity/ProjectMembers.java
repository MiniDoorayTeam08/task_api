package com.nhnacademy.midoo.taskapi.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project_member")
public class ProjectMembers {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Projects project;


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
        Integer projectId;
    }
}
