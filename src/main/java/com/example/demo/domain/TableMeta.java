package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class TableMeta {

    @JsonIgnore
    @Column(name = "flush_table_id")
    private long flushTableId;

    @JsonIgnore
    @Column(name = "id")
    private long id;

    private List<FirstCategory> first_category;

    public List<FirstCategory> getFirst_category() {
        return first_category;
    }

    public void setFirst_category(List<FirstCategory> first_category) {
        this.first_category = first_category;
    }

    public long getFlushTableId() {
        return flushTableId;
    }

    public void setFlushTableId(long flushTableId) {
        this.flushTableId = flushTableId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
