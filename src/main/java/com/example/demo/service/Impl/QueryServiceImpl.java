package com.example.demo.service.Impl;

import com.deidara.hutoolplus.Dict;
import com.example.demo.dao.QueryDao;
import com.example.demo.domain.FirstCategory;
import com.example.demo.domain.SecondCategory;
import com.example.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
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
        String xr= null;
        return Dict.create().set("first_category",firstCategoryDict);
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
