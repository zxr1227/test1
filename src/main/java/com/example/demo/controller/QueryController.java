package com.example.demo.controller;

import com.example.demo.dao.FlushTableDao;
import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.domain.TableMeta;
import com.example.demo.ov.TableMetaOV;
import com.example.demo.service.FlushTableService;
import com.example.demo.service.QueryService;
import com.example.demo.util.DataVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;



@RestController
@RequestMapping(value = "/table")
public class QueryController {
    @Autowired
    private QueryService queryService;

    @Autowired
    private FlushTableService flushTableService;

    @GetMapping("/category_tree")
    public DataVO<TableMeta> query(Long tableId){
        return queryService.getAllFirstCategory(tableId);
    }

    @GetMapping("/merge_category_tree")
    public DataVO<List<TableMetaOV>> flushTable(Long flushTableId){
        return flushTableService.getFlushTable(flushTableId);
    }

}
