package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deidara.dynamicds.actable.annotation.Column;
import lombok.Data;

@Data
@TableName
public class FirstCategory {

    @Column(comment = "一级分类id")
    private Long id;
    @Column(comment = "一级分类名称")
    private String name;
    @Column(comment = "排序号")
    private Integer sortNum;
    @Column(comment = "表id")
    private Long tableId;

}
