package com.example.demo.service;

import com.example.demo.domain.TableMeta;

import java.util.List;

public interface QueryService {

    List<TableMeta> getAllFirstCategory(Long tableId);

}
