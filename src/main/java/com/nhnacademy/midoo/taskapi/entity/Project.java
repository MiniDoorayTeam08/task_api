package com.nhnacademy.midoo.taskapi.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_explain")
    private String projectExplain;

    @Column(name = "project_status")
    private String projectStatus;
}
