package com.example.demo.service.Impl;

import com.deidara.hutoolplus.Dict;
import com.example.demo.dao.QueryDao;
import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;

    @Override
    public Dict getAllFirstCategory(Long tableId) {
        List<FirstCategory> firstCategories = queryDao.selectByMap(Dict.create().set("table_id",tableId));
        List<Dict> firstCategoryDict = firstCategories.stream().map(x ->{
            List<SecondCategory> secondCategories = queryDao.selectByMap(Dict.create().set("first_category_id",x.getId()));
            List<Dict> secondCategoryList = secondCategories.stream().map(y -> Dict.create().set("id",y.getId()).set("name",y.getName())).collect(Collectors.toList());
            Dict secondCategoryDict = Dict.create();
            secondCategoryDict.set("id",x.getId()).set("name",x.getName()).set("second_category",secondCategoryList);
            return secondCategoryDict;
        }).collect(Collectors.toList());
        String ax = null;
        int i = 0;
        return Dict.create().set("first_category",firstCategoryDict);
    }
}
