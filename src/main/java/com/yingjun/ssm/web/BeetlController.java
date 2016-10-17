package com.yingjun.ssm.web;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * Created by dou on 2016/10/16.
 */
@Controller
@RequestMapping("/beetl")
public class BeetlController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "beetlGroupUtilConfiguration")
    BeetlGroupUtilConfiguration config;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) throws IOException {

        //BeetlGroupUtilConfiguration config = (BeetlGroupUtilConfiguration) this.getApplicationContext().getBean("beetlConfig");
        GroupTemplate group = config.getGroupTemplate();

        GroupTemplate groupTemplate = config.getGroupTemplate();
        groupTemplate.getTemplate("model.btl");
        Template entity = groupTemplate.getTemplate("model.btl");
        entity.binding("className", "testModel");
        entity.binding("model", model);

        List<Map<String, String>> columns = new ArrayList<Map<String,String>>();

        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("name", "dd");//转小写 否则生成的model是大写
        m1.put("type", "string");

        Map<String, String> m2 = new HashMap<String, String>();
        m2.put("name", "tt");//转小写 否则生成的model是大写
        m2.put("type", "string");

        columns.add(m1);
        columns.add(m2);

        entity.binding("columns", columns);

        String basePath = this.getClass().getClassLoader().getResource("/").getPath();
        basePath = basePath.substring(0,basePath.indexOf("target"));
        basePath = basePath + "src/main/webapp/files";
        File entityFile = new File(basePath+"/usertestModel.js");
        entityFile.createNewFile();
        Writer entityWriter = new FileWriterWithEncoding(entityFile,"utf-8");
        entity.renderTo(entityWriter);
        entityWriter.close();

        return "userlist";
    }



}
