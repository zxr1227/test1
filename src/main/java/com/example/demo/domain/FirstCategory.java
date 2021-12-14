package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class FirstCategory {

    @JsonIgnore
    @Column(name = "tableId")
    private long tableId;

    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    private List<SecondCategory> second_category;

    public List<SecondCategory> getSecond_category() {
        return second_category;
    }

    public void setSecond_category(List<SecondCategory> second_category) {
        this.second_category = second_category;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

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
}
