package com.example.demo.service;

import com.example.demo.domain.FirstCategory;

import com.example.demo.domain.SecondCategory;
import com.example.demo.domain.TableMeta;
import com.example.demo.util.DataVO;

import java.util.List;

public interface QueryService {

    DataVO<TableMeta> getAllFirstCategory(Long tableId);

    List<FirstCategory> selectCategory(Long tableId);

    List<TableMeta> ddd();
}
