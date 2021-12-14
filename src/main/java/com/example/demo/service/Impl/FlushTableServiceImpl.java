package com.example.demo.service.Impl;

import com.example.demo.dao.FlushTableDao;
import com.example.demo.ov.FirstCategoryOV;
import com.example.demo.ov.SecondCategoryOV;
import com.example.demo.ov.TableMetaOV;
import com.example.demo.service.FlushTableService;
import com.example.demo.util.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlushTableServiceImpl implements FlushTableService {

    @Autowired
    private FlushTableDao flushTableDao;

    @Override
    public DataVO<List<TableMetaOV>> getFlushTable(Long flushTableId) {
        List<TableMetaOV> metas = new ArrayList<>();
        DataVO<List<TableMetaOV>> data = new DataVO<>();
        for(TableMetaOV meta:flushTableDao.tableMeta()){
            if(meta.getFlushTableId() == flushTableId){
                metas.add(meta);
            }
        }
        for(TableMetaOV meta:metas){
            List<FirstCategoryOV> parents = getParent(meta.getId());
            meta.setFirst_category(parents);
        }
        data.setData(metas);
        return data;
    }
    //找一级类目的方法
    private List<FirstCategoryOV> getParent(Long tableId){
        List<FirstCategoryOV> parent = new ArrayList<>();
        for(FirstCategoryOV firstCategory:flushTableDao.getFirstCategory()){
            if(firstCategory.getTableId() == tableId){
                       parent.add(firstCategory);
               }
            }
        for(FirstCategoryOV firstCategory:parent){
            List<SecondCategoryOV> children = getChild(firstCategory.getId());
            firstCategory.setSecond_category(children);
        }
        for(int i = 0; i < parent.size(); i++) {
            List<Long> list = new ArrayList<>();
            list.add(parent.get(i).getId());
            for (int j = i + 1; j < parent.size(); j++) {
                if (parent.get(i).getName().equals(parent.get(j).getName())) {
                    list.add(parent.get(j).getId());
                    parent.remove(j);
                }
            }
            parent.get(i).setIds(list);
        }
        return parent;
    }
    //找二级类目的方法
    private List<SecondCategoryOV> getChild(Long id){
        List<SecondCategoryOV> children = new ArrayList<>();

        for(SecondCategoryOV secondCategory:flushTableDao.getAllSecondCategory()){
            if(id == secondCategory.getFirstCategoryId()){
                children.add(secondCategory);
            }
        }
        for(int i = 0; i < children.size(); i++){
            List<Long> list = new ArrayList<>();
            list.add(children.get(i).getId());
            for(int j = i+1; j < children.size(); j++){
                if(children.get(i).getName().equals(children.get(j).getName())){
                    list.add(children.get(j).getId());
                    children.remove(j);
                }
            }
            children.get(i).setIds(list);
        }
        return children;
    }
}
