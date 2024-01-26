package com.nhnacademy.midoo.taskapi.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMembers;
}
