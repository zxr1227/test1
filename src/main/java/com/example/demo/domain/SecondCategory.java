package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;

@Data
public class SecondCategory  {

    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "first_category_id")
    private long firstCategoryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(long firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }
}
