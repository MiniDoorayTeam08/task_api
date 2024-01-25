package com.nhnacademy.midoo.taskapi.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "milestone")
public class Milestones {
    @Id
    @Column(name = "milestone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer milestoneId;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @JoinColumn(name = "project_id")
    @ManyToOne
    private Projects projects;
}
