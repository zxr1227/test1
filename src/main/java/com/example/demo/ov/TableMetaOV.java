package com.example.demo.ov;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.Column;
import java.util.List;

@Data
public class TableMetaOV {

    private List<FirstCategoryOV> first_category ;

    public List<FirstCategoryOV> getFirst_category() {
        return first_category;
    }

    public void setFirst_category(List<FirstCategoryOV> first_category) {
        this.first_category = first_category;
    }

}
