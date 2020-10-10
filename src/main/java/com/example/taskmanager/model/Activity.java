package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="activity")
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="name_en")
    private String nameEn;

    @Column(name="name_ua")
    private String nameUa;

    @Column(name = "description", nullable = true)
    private String  description;

    @Column(name="description_en")
    private String descriptionEn;

    @Column(name="description_ua")
    private String descriptionUa;

    //mappings

    @ManyToOne
    @JoinColumn(name="id_category", nullable=false)
    private Category category;

    @OneToMany(mappedBy="activity")
    private Set<UserActivity> userActivities = new HashSet<UserActivity>(); ;

}
