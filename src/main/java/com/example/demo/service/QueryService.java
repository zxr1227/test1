package com.example.demo.service;

import com.example.demo.domain.TableMeta;

import java.util.List;

public interface QueryService {

    TableMeta getAllFirstCategory(Long tableId);

    void getXml();
}
