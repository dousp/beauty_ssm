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
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dou on 2016/10/16.
 */
@Controller
@RequestMapping("/beetl")
public class BeetlController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BeetlGroupUtilConfiguration config;
    @Autowired
    WebServiceTemplate webServiceTemplate;

    private static final String MESSAGE =
       "<queryPeopleByID  xmlns=\"http://test.cxfws.com\">1231ss</queryPeopleByID> ";

    private static final String testMSg =

                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                +" xmlns:typ=\"http://xmlns.oracle.com/apps/sales/custExtn/extnService/types/\" "
                +" xmlns:typ1=\"http://xmlns.oracle.com/adf/svc/types/\"> "
                +" <soapenv:Header />"
                +" <soapenv:Body>"
                +"  <typ:findEntity>"
                +"     <typ:findCriteria>"
                +"        <typ1:fetchStart>0</typ1:fetchStart>"
                +"        <typ1:fetchSize>-1</typ1:fetchSize>"
                +"        <typ1:filter>"
                +"           <typ1:conjunction>And</typ1:conjunction>"
                +"           <typ1:group>"
                +"              <typ1:conjunction>AndNot</typ1:conjunction>"
                +"              <typ1:upperCaseCompare>false</typ1:upperCaseCompare>"
                +"              <typ1:item>"
                +"                 <typ1:conjunction>Not</typ1:conjunction>"
                +"                 <typ1:upperCaseCompare>false</typ1:upperCaseCompare>"
                +"                 <typ1:attribute>SampleInputStatus_c</typ1:attribute>"
                +"                 <typ1:operator>=</typ1:operator>"
                +"                 <!--You have a CHOICE of the next 2 items at this level-->"
                +"                 <!--Zero or more repetitions:-->"
                +"                 <typ1:value>C</typ1:value>"
                +"              </typ1:item>"
                +"              <typ1:item>"
                +"                 <typ1:conjunction>Not</typ1:conjunction>"
                +"                 <typ1:upperCaseCompare>false</typ1:upperCaseCompare>"
                +"                 <typ1:attribute>LastUpdateDate</typ1:attribute>"
                +"                 <typ1:operator>AFTER</typ1:operator>"
                +"                 <!--You have a CHOICE of the next 2 items at this level-->"
                +"                 <!--Zero or more repetitions:-->"
                +"                 <typ1:value>2016-10-20T07:17:15.001Z</typ1:value>"
                +"              </typ1:item>"
                +"           </typ1:group>"
                +"           <!--Zero or more repetitions:-->"
                +"        </typ1:filter>"
                +"        <!--Optional:-->"
                +"        <typ1:sortOrder>"
                +"           <!--1 or more repetitions:-->"
                +"           <typ1:sortAttribute>"
                +"              <typ1:name>SampleInputStatus_c</typ1:name>"
                +"              <typ1:descending>false</typ1:descending>"
                +"           </typ1:sortAttribute>"
                +"        </typ1:sortOrder>"
                +"        <typ1:excludeAttribute />"
                +"     </typ:findCriteria>"
                +"     <typ:findControl>"
                +"        <typ1:retrieveAllTranslations>false</typ1:retrieveAllTranslations>"
                +"     </typ:findControl>"
                +"     <typ:objectName>BRSampleNIPT_c</typ:objectName>"
                +"  </typ:findEntity>"
                +"</soapenv:Body>"
                +"</soapenv:Envelope>";

    @RequestMapping(value = "/ws", method = RequestMethod.GET)
    public String ws(Model model) {

        String uri = webServiceTemplate.getDefaultUri();
        System.out.println(uri);

        StreamSource source = new StreamSource(new StringReader(testMSg));
        StreamResult result = new StreamResult(System.out);
        Boolean tag = webServiceTemplate.sendSourceAndReceiveToResult(source, result);
        System.out.println(tag);


        return "userlist";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) throws IOException {

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

        String projectPath = this.getClass().getClassLoader().getResource("/").getPath();
        String targetPath = "src/main/webapp/files";
        projectPath = projectPath.substring(0,projectPath.indexOf("target"));
        String basePath = projectPath + targetPath;

        File entityFile = new File(basePath+"/usertestModel.js");
        entityFile.createNewFile();
        Writer entityWriter = new FileWriterWithEncoding(entityFile,"utf-8");
        entity.renderTo(entityWriter);
        entityWriter.close();

        return "userlist";
    }



}
