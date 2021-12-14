package com.example.demo.dao;

import com.example.demo.domain.TableMeta;
import com.example.demo.ov.FirstCategoryOV;
import com.example.demo.ov.SecondCategoryOV;
import com.example.demo.ov.TableMetaOV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FlushTableDao {

    @Select("select * from first_category")
    List<FirstCategoryOV> getFirstCategory();

    @Select("select * from second_category")
    List<SecondCategoryOV> getAllSecondCategory();

    @Select("select * from table_meta")
    List<TableMetaOV> tableMeta();

}
