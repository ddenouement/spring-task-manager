package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="category")
public class Category {
    public Category(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name", unique=true)
    private String name;

    @Column(name="name_en", unique=true)
    private String nameEn;

    @Column(name="name_ua", unique=true)
    private String nameUa;

    @ManyToMany(mappedBy = "categories")
    private Set<Activity> activities = new HashSet<Activity>();

    @PreRemove
    public void removeActivitiesFromCategories() {
         for (Activity u : activities) {
            u.getCategories().remove(this);
        }
    }
}
