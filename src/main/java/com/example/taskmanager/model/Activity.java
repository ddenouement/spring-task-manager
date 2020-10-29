package com.example.taskmanager.model;

import com.example.taskmanager.dto.ActivityDTO;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@Table(name = "activity")
@AllArgsConstructor
@Builder
public class Activity {

    public Activity() {
        this.categories = new ArrayList<>();
        this.enabled = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name = "enabled", nullable = false,  columnDefinition = "BOOLEAN")
    private Boolean enabled = true;

    public Boolean getEnabled() {
        if (enabled == null) return true;
        return enabled;
    }

    public void setEnabled(Boolean a) {
        if (a == null) a = true;
        this.enabled = a;
    }


    @Formula("(SELECT COUNT(user_activity.id_activity) " +
            "FROM activity LEFT JOIN user_activity on activity.id = user_activity.id_activity " +
            "WHERE user_activity.status = 'ASSIGNED' and activity.id=user_activity.id " +
            "GROUP BY activity.id)")
    private Integer people;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "name_en", unique = true)
    private String nameEn;

    @Column(name = "name_ua", unique = true)
    private String nameUa;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "description_ua")
    private String descriptionUa;

    //mappings
    @ManyToMany
    //@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
  /*  @JoinTable(
            name = "activity_category",
            joinColumns = {@JoinColumn(name = "activity_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )*/
    //  @ElementCollection
    private List<Category> categories = new ArrayList<>();


    @OneToMany(mappedBy = "activity")
    private Set<UserActivity> userActivities = new HashSet<UserActivity>();

    @PreRemove
    private void removeCategoriesFromActivities() {
        for (Category u : categories) {
            u.getActivities()
                    .remove(this);
        }
    }

    public ActivityDTO getDTO() {
        ActivityDTO result = ActivityDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .nameEn(this.getNameEn())
                .nameUa(this.getNameUa())
                .description(this.description)
                .descriptionEn(this.descriptionEn)
                .descriptionUa(this.descriptionUa)
                .categoryIds(categories.stream().map(Category::getId).collect(Collectors.toSet()))
                .build();
        return result;
    }
}
