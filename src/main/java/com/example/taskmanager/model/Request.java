package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private ZonedDateTime createdOn;

    //mappings

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User user;

    @Column(name="status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name="motif")
    @Enumerated(value = EnumType.STRING)
    private Motif motif;

    @ManyToOne
    @JoinColumn(name="id_user_activity", nullable=false)
    private UserActivity task;


}
