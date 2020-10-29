package com.example.taskmanager.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder(access=AccessLevel.PUBLIC)
public class ActivityDTO {
    public ActivityDTO() {
        categoryIds = new HashSet<>();
    }

    int id;
    String name;
    String nameEn;
    String nameUa;
    String description;
    String descriptionEn;
    String descriptionUa;
    Set<Integer> categoryIds;
}
