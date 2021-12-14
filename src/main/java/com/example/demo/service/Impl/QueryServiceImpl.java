package com.example.demo.service.Impl;

import com.example.demo.dao.QueryDao;
import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.domain.TableMeta;
import com.example.demo.service.QueryService;
import com.example.demo.util.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import java.util.*;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;

    @Override
    public DataVO<TableMeta> getAllFirstCategory(Long tableId) {
        List<TableMeta> metas = new ArrayList<>();
        DataVO<TableMeta> data = new DataVO<>();
        for(TableMeta meta:queryDao.tableMeta()){
            if(meta.getId() == tableId){
                metas.add(meta);
            }
        }
        for(TableMeta meta:metas){
            List<FirstCategory> parents = getParent(tableId);
            meta.setFirst_category(parents);
        }
        for(TableMeta meta:metas){
            data.setData(meta);
        }
       return data;
    }
    //找一级目录的方法
    private List<FirstCategory> getParent(Long tableId){
        List<FirstCategory> parent = new ArrayList<>();
        for(FirstCategory firstCategory:queryDao.getFirstCategory()){
            if(firstCategory.getTableId() == tableId){
                parent.add(firstCategory);
            }
        }
        for(FirstCategory firstCategory:parent){
            List<SecondCategory> children = getChild(firstCategory.getId());
            firstCategory.setSecond_category(children);
        }
        return parent;
    }
    //找二级目录的方法
    private List<SecondCategory> getChild(Long id){
        //存放子节点的集合
        List<SecondCategory> children = new ArrayList<>();
            for(SecondCategory secondCategory: queryDao.getAllSecondCategory()){
                if(id == secondCategory.getFirstCategoryId()){
                    children.add(secondCategory);
                }
            }

        return children;
    }

    @Override
    public List<FirstCategory> selectCategory(Long tableId) {
        return null;
    }

    @Override
    public List<TableMeta> ddd(){
        return queryDao.ddd();
    }
}
