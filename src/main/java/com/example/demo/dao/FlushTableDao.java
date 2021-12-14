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

    @Select("select s.first_category_id,s.id,s.name from first_category f join table_meta t " +
            "on f.table_id = t.id join second_category s " +
            "on s.first_category_id = f.id where t.flush_table_id = #{flushTableId}")
    List<SecondCategoryOV> getSecondCategory(Long flushTableId);

    @Select("select f.id,f.name from first_category f " +
            "join table_meta t on f.table_id = t.id where t.flush_table_id = #{flushTableId};")
    List<FirstCategoryOV> getFirstCategory(Long flushTableId);

}
