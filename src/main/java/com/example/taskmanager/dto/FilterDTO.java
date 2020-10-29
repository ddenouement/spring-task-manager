package com.example.taskmanager.dto;

import com.example.taskmanager.util.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class FilterDTO {
    public FilterDTO() {
        this.sort = 0;
        this.page=0;
        this.categoryIds = new ArrayList<>();
    }
    private int page;

    private int sort;

    public SortParam getSortBy() {
        return SortParam.values()[sort];
    }

    private List<Integer> categoryIds;
}
