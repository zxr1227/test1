package com.example.demo.service.Impl;

import com.example.demo.dao.QueryDao;
import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.domain.TableMeta;
import com.example.demo.service.QueryService;
import lombok.extern.log4j.Log4j2;
import org.apache.oozie.fluentjob.api.action.SparkAction;
import org.apache.oozie.fluentjob.api.action.SparkActionBuilder;
import org.apache.oozie.fluentjob.api.workflow.WorkflowBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j2
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;

    @Override
    public TableMeta getAllFirstCategory(Long tableId) {
        TableMeta metas = new TableMeta();
        metas.setFirst_category(getParent(tableId));
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

    @Override
    public void getXml(){
            String filePath = "C:\\Users\\test\\Desktop\\streaming-crm-1.0\\bin\\bi_order_report_stat\\bi_order_report_stat.py";
            String scriptFile = "/user/hive/udg/bddwh-stream-1.0/bin/saas_dwd/saas_dwd_crm_order_item_inc_rt.py";
            File file = new File(filePath);
            BufferedReader reader = null;
            String tempString = null;
            String sb = " ";
            Integer a = 1;
            System.out.println(file.getName());
            int line = 1;
            try {
// System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
                while ((tempString = reader.readLine()) != null) {
                    sb+="\n";
                    sb+=tempString;
                    line++;
                }
                Map<String,String> map = new HashMap<>();
                WorkflowBuilder workflowBuilder = new WorkflowBuilder().withName("test");
                String workflowXml = workflowBuilder.withDagContainingNode(buildSparkAction(scriptFile, "test", sb, map)).build().asXml();
                System.out.println(workflowXml);
//            System.out.println(sb);
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    public SparkAction buildSparkAction(String scriptFileStr, String fullName, String reader, Map<String,String> map) throws IOException {
        File scriptFile = new File(scriptFileStr);
        log.info("fullName===={}",fullName);
        String[] split = (scriptFile.getParentFile().getAbsolutePath() + "/").split("/bin/");
        String projectName = new File(split[0]).getName();
        log.info("projectName===={}", projectName);
        String[] files = reader.split("\n");
        String pattern = "spark_config(\\[).*?(\\])";
        for(String file:files){
            Pattern p =Pattern.compile(pattern);
            if(p.matcher(file).find()){
                String[] temp = file.split("\'");
                String key = temp[1];
                String value = temp[3];
                map.put(key,value);
            }
        }
        SparkActionBuilder sparkActionBuilder = SparkActionBuilder.create()
                .withActionName(fullName.split("\\.")[1])
                .withResourceManager("yarnRM")
                .withNameNode("hdfs://nameservice1")
                .withMode(map.get("deploy-mode"));
        if(map.containsKey("master")){
            sparkActionBuilder.withMaster(map.get("master"));
        }
        if(map.containsKey("name")){
            sparkActionBuilder.withName(map.get("name"));
        }
        if(map.containsKey("class")){
            sparkActionBuilder.withActionClass(map.get("class"));
        }
        return sparkActionBuilder.build();
    }
}
