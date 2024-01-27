package com.nhnacademy.midoo.taskapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "milestones")
public class Milestone {
    @Id
    @Column(name = "milestone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    @Column(name = "milestone_name")
    private String milestoneName;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
