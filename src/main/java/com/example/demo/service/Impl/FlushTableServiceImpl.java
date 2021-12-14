package com.example.demo.service.Impl;

import com.example.demo.dao.FlushTableDao;
import com.example.demo.ov.FirstCategoryOV;
import com.example.demo.ov.SecondCategoryOV;
import com.example.demo.ov.TableMetaOV;
import com.example.demo.service.FlushTableService;
import org.hibernate.metamodel.internal.JpaStaticMetaModelPopulationSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlushTableServiceImpl implements FlushTableService {

    @Autowired
    private FlushTableDao flushTableDao;

    @Override
    public TableMetaOV getFlushTable(Long flushTableId) {
        TableMetaOV metas = new TableMetaOV();
        List<FirstCategoryOV> firstCategoryOVS = flushTableDao.getFirstCategory(flushTableId);
        List<SecondCategoryOV> secondCategoryOVS = flushTableDao.getSecondCategory(flushTableId);
        for(int i = 0; i < secondCategoryOVS.size(); i++){
            List<Long> ids = new ArrayList<>();
            ids.add(secondCategoryOVS.get(i).getId());
            for(int j = i+1; j < secondCategoryOVS.size(); j++){
                if (secondCategoryOVS.get(i).getName().equals(secondCategoryOVS.get(j).getName())) {
                    ids.add(secondCategoryOVS.get(j).getId());
                    secondCategoryOVS.remove(j);
                }
            }
            secondCategoryOVS.get(i).setIds(ids);
        }
        for(int i = 0; i < firstCategoryOVS.size(); i++){
            List<Long> ids = new ArrayList<>();
            ids.add(firstCategoryOVS.get(i).getId());
            for(int j = i+1; j < firstCategoryOVS.size(); j++){
                if (firstCategoryOVS.get(i).getName().equals(firstCategoryOVS.get(j).getName())) {
                    ids.add(firstCategoryOVS.get(j).getId());
                    firstCategoryOVS.remove(j);
                }
            }
            firstCategoryOVS.get(i).setIds(ids);
        }
        for(int i = 0; i < firstCategoryOVS.size(); i++){
            List<SecondCategoryOV> list = new ArrayList<>();
            for(int j = 0; j < secondCategoryOVS.size(); j++){
                if(firstCategoryOVS.get(i).getId() == secondCategoryOVS.get(j).getFirstCategoryId()){
                    list.add(secondCategoryOVS.get(j));
                }
            }
            firstCategoryOVS.get(i).setSecond_category(list);
        }
        metas.setFirst_category(firstCategoryOVS);
        return metas;
    }
}
