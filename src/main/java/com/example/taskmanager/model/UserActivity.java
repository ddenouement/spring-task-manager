package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "time_elapsed_hrs ", nullable = true)
    private int timeSpentInHours;

    @Column(name = "date_end", nullable = true)
    private ZonedDateTime finishedOn;

    //mappings

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="id_activity", nullable=false)
    private Activity activity;

    @OneToMany(mappedBy="task")
    private Set<Request> requests = new HashSet<Request>(); ;


    @Column(name="progress")
    @Enumerated(EnumType.STRING )
    private Progress progress;

}
