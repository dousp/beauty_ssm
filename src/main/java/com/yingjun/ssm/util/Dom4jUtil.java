package com.yingjun.ssm.util;

import org.apache.commons.collections.map.HashedMap;
import org.dom4j.*;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dou on 2016/11/7.
 */
public class Dom4jUtil {

    public static void main(String[] args) throws Exception
    {
        System.out.println("=====================================");

        String str = "";
        List<Map<String,Object>> list1 = getResultByString(str);
        List<Map<String,Object>> list2 = getResultByFile(new File("ws-response.xml"));

        System.out.println("=====================================");

    }

    /**
     * 解析xml文件
     * @param file
     * @return
     * @throws DocumentException
     */
    public static List<Map<String,Object>> getResultByFile(File file) throws DocumentException {
        // List<Map<String,Object>> list = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(file);
        Element root = doc.getRootElement();
        return getResultList(root);
        // List<Element> resultList = root.elements("result");
        // for (Element element : resultList) {
        //     Map<String,Object> map = parseTagResult(element);
        //     list.add(map);
        // }
        //
        // return list;
    }

    /**
     * 解析xml字符串
     * @param finder
     * @return
     * @throws DocumentException
     */
    public static List<Map<String,Object>> getResultByString(String finder) throws DocumentException {

        // List<Map<String,Object>> list = new ArrayList<>();
        Document doc = DocumentHelper.parseText(finder);
        Element root = doc.getRootElement();
        return getResultList(root);
        // List<Element> resultList = root.elements("result");
        // for (Element element : resultList) {
        //     Map<String,Object> map = parseTagResult(element);
        //     list.add(map);
        // }
        //
        // return list;
    }

    /**
     * 获取result标签的解析结果
     * @param root
     * @return
     */
    public static List<Map<String,Object>> getResultList(Element root){

        List<Map<String,Object>> list = new ArrayList<>();

        List<Element> resultList = root.elements("result");
        for (Element element : resultList) {
            Map<String,Object> map = parseResultTag(element);
            list.add(map);
        }
        return list;
    }

    /**
     * 解析单个result标签
     * @param element
     * @return
     */
    public static Map<String,Object> parseResultTag(Element element){

        Map<String,Object> map = new HashedMap();

        //List<Element>  elementLists = element.elements();
        for(Iterator iter = element.elementIterator(); iter.hasNext();)
        {
            Element e = (Element)iter.next();

            //正常有值
            map.put(e.getName(),e.getTextTrim().replace("\n","").trim());
            //属性赋值
            for(Iterator iter2 = e.attributeIterator(); iter2.hasNext();){
                Attribute attr = (Attribute) iter2.next();
                if(attr.getName().equals("nil")){
                    map.put(e.getName(),attr.getText().replace("\n","").trim());
                }
            }

            //若是当前节点下有子节点
            if(e.elements().size()>0){
                Map<String,Object> childMap =  parseResultTag(e);
                map.put(e.getName(),childMap);
            }

        }
        return map;
    }


}
