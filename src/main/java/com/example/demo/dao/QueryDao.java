package com.example.demo.dao;


import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.domain.TableMeta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface QueryDao {

    @Select("select name,id from first_category where table_id = #{tableId}")
    List<FirstCategory> selectByTableId(Long tableId);

    @Select("select id,name from second_category where first_category_id = #{firstCategoryId}")
    List<SecondCategory> selectByFid(Long firstCategoryId);

    @Select("select id from table_meta where id = #{id}")
    List<TableMeta> selectById(Long id);
}
