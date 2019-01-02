package com.hb.scms.main.freemarker;

import com.hb.scms.util.BeanCommon;
import com.hb.scms.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateMapping extends FmkBase {

    public void process(String tableName , List<BeanCommon> ls ) throws IOException, TemplateException {
        String className=StringUtil.underline2Camel(tableName, false);
        //2.向root中放入模版中所需信息
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("attrs",ls);
        root.put("packageName", "com.dao");
        root.put("ModelPackageName", "com.model");
        root.put("className",  className);
        root.put("tableName",  tableName);
        root.put("primaryColumn",  ls.get(0).getPrimary());
        root.put("primaryProp",  StringUtil.underline2Camel(ls.get(0).getPrimary(), true));
        //3.将模版进行指定文件的输出
        String outPath=System.getProperty("user.dir")+"/src/main/resources"+"/"+OUT_PUT_PATH+"/"+className+"Mapper.xml";
        write(root,outPath);

    }
    /*
     * 执行入口
     */
    public static void main(String[] args) throws Exception {

        CreateMapping hfm = new CreateMapping();
        hfm.init(args[0]);
        hfm.preparedData();

    }



}
