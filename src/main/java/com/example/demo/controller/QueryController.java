package com.example.demo.controller;

import com.deidara.hutoolplus.Dict;
import com.example.demo.service.QueryService;
import com.example.demo.util.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/table")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/category_tree")
    public DataVO<Dict> query(Long tableId){
        return new DataVO(queryService.getAllFirstCategory(tableId));
    }


}
