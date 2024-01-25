package com.nhnacademy.midoo.taskapi.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "milestones")
public class Milestone {
    @Id
    @Column(name = "milestone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer milestoneId;

    @Column(name = "milestone_name")
    private String milestoneName;

    @Column(name = "milestone_start_date")
    private LocalDateTime milestoneStartDate;

    @Column(name = "milestone_end_date")
    private LocalDateTime milestoneEndDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
