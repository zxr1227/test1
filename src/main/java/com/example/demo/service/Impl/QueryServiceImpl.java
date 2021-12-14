package com.example.demo.service.Impl;

import com.example.demo.dao.QueryDao;
import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.domain.TableMeta;
import com.example.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;

    @Override
    public List<TableMeta> getAllFirstCategory(Long tableId) {
        List<TableMeta> metas = queryDao.selectById(tableId);
        metas.get(0).setFirst_category(getParent(tableId));
       return metas;
    }
    private List<FirstCategory> getParent(Long tableId){
        List<FirstCategory> parent = queryDao.selectByTableId(tableId);
        for(FirstCategory firstCategory:parent){
            List<SecondCategory> children = queryDao.selectByFid(firstCategory.getId());
            firstCategory.setSecond_category(children);
        }
        return parent;
    }
}
