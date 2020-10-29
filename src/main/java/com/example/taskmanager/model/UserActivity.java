package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user_activity")
public class UserActivity {
    public UserActivity (){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "time_elapsed_hrs ", nullable = true)
    private int timeSpentInHours;

    @Column(name = "date_end", nullable = true)
    private ZonedDateTime finishedOn;



    public Date finishedDate(){
        if(finishedOn==null) return null;
        return Date.from(finishedOn.toInstant());
    }

    @Column(name = "created_request_at", nullable = false)
    private ZonedDateTime createdOn;

    //mappings

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="id_activity", nullable=false)
    private Activity activity;


    @Column(name="status")
    @Enumerated(EnumType.STRING )
    private Status status;

}
