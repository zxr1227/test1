package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deidara.dynamicds.actable.annotation.Column;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Data
@TableName
public class TableMeta {

    @Column(comment = "表id")
    private Long id;
    @Column(comment = "表英文名称")
    private String nameEn;
    @Column(comment = "表中文名称")
    private String nameCn;
    @Column(comment = "表英文全称，{layerName}.{layerName}_{businessPlateName}_{dataDomainName}_{nameEn}_{frequencyName}")
    private String fullNameEn;
    @Column(comment = "排序号")
    private Integer sortNum;
    @Column(type = "text", comment="表描述信息")
    private String description;
    @Column(comment = "表状态，0草稿，1已试运行，2已发布 ,3已下线")
    private Integer status;

    @Column(comment = "触发类型，1时间触发，2事件触发")
    private Integer triggerType;
    @Column(comment = "调度编号")
    private String scheduleNum;
    @Column(comment = "指定调度计划中的调度频率标识，例如取值monthly、weekly、daily、hourly")
    private String frequencyName;
    @Column(comment = "调度计划中的调度频率crontab表达式（linux语法）")
    private String frequencyCron;
    @Column(comment = "调度计划起始时间，时间格式为yyyy-MM-dd HH:mm")
    private String startTime;
    @Column(comment = "调度计划终止时间，时间格式为yyyy-MM-dd HH:mm")
    private String endTime;
    @Column(comment = "依赖的tableId列表，英文逗号分隔")
    private String parentTableIds;

    @Column(comment = "表的数据计算完后，将数据刷到另外一张宽表")
    private Long flushTableId;
    @Column(comment = "数据计算完后推送目标列表,英文逗号分隔，clickhouse、kafka")
    private String pushTargets;
    @Column(comment = "任务类型，0无任务、1sql任务、2脚本任务、3宽表任务")
    private Integer taskType;
    @Column(comment = "任务脚本路径")
    private String scriptFile;
    @Column(comment = "表所在的workflowId")
    private Long workflowId;
    @Column(comment = "数据域id")
    private Long dataDomainId;

}
