package com.example.demo.ov;

import com.example.demo.domain.SecondCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class FirstCategoryOV {
    @JsonIgnore
    @Column(name = "tableId")
    private long tableId;

    @JsonIgnore
    @Column(name = "id")
    private Long id;

    private List<Long> ids;

    @Column(name = "name")
    private String name;

    private List<SecondCategoryOV> second_category ;

    public List<SecondCategoryOV> getSecond_category() {
        return second_category;
    }

    public void setSecond_category(List<SecondCategoryOV> second_category) {
        this.second_category = second_category;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> fid) {
        this.ids = fid;
    }
}
