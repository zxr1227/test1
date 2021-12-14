package com.example.demo.ov;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class SecondCategoryOV {

    @JsonIgnore
    @Column(name = "id")
    private Long id;

    private List<Long> ids;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "first_category_id")
    private long firstCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
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
