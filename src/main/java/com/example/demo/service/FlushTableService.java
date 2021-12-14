package com.example.demo.service;

import com.example.demo.domain.TableMeta;
import com.example.demo.ov.TableMetaOV;
import com.example.demo.util.DataVO;

import java.util.List;

public interface FlushTableService {

    DataVO<List<TableMetaOV>> getFlushTable(Long flushTableId);

}
