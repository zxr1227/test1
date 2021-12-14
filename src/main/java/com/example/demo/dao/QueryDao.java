package com.example.demo.dao;


import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.domain.TableMeta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface QueryDao {

    @Select("select * from first_category")
    List<FirstCategory> getFirstCategory();

    @Select("select * from second_category")
    List<SecondCategory> getAllSecondCategory();

    @Select("select * from table_meta")
    List<TableMeta> tableMeta();

    @Select("select * from second_category where id=#{id}")
    List<SecondCategory> getSecondCategoryById(Long id);

    @Select("select * from table_meta t join first_category f on t.id = f.table_id")
    List<TableMeta> ddd();

}
