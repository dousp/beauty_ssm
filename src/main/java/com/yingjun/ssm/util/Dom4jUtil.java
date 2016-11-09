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

        // String str = "";
        // List<Map<String,Object>> list1 = getResultByString(str);
        // List<Map<String,Object>> list2 = getResultByFile(new File("ws-response.xml"));

        double d = 42681.40277777778d;

        List list = parseXml(new File("student2.xml"));
        System.out.println(list.size());

        System.out.println("=====================================");

    }

    /**
     * 解析xml文件
     * @param file
     * @return
     * @throws DocumentException
     */
    public static List<Map<String,Object>> getResultByFile(File file) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(file);
        Element root = doc.getRootElement();
        return getResultList(root);
    }

    /**
     * 解析xml字符串
     * @param finder
     * @return
     * @throws DocumentException
     */
    public static List<Map<String,Object>> getResultByString(String finder) throws DocumentException {

        Document doc = DocumentHelper.parseText(finder);
        Element root = doc.getRootElement();
        return getResultList(root);
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



    public static List<Map<String,Object>> parseXml(File file) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(file);
        Element root = doc.getRootElement();
        Element header = root.element("Header");
        Element body = root.element("Body");

        List<Element> lists =  body.elements();
        List<Element> results = lists.get(0).elements();

        List<Map<String,Object>> list = new ArrayList<>();
        for (Element result : results) {
            Map<String,Object> map = parseResultTag(result);
            list.add(map);
        }

        return list;
    }

    public List<Element> getResult(Element element){

        // for(Iterator iter = element.elementIterator(); iter.hasNext();) {
        //     Element e = (Element)iter.next();
        //     if(e.getName().equals("result")){
        //         return element.elements();
        //     }
        // }
        //
        // // 当前节点下面子节点迭代器
        // Iterator<Element> it = element.elementIterator();
        // // 遍历
        // while (it.hasNext()) {
        //     // 获取某个子节点对象
        //     Element e = it.next();
        //     // 对子节点进行遍历
        //     List<Element> list = getResult(e);
        //
        //     if(list != null){
        //         return list;
        //     }
        // }
        return null;
    }

    public static Element getRootForResult(Element node, String target) {


        System.out.println("-------开始新节点-------------");
        // 当前节点的名称、文本内容和属性
        System.out.println("当前节点名称：" + node.getName());// 当前节点名称

        Element finder = null;

        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();// 所有一级子节点的list

        for (final Element e : listElement) {// 遍历所有一级子节点

            if(e.getName().equals(target)){
                finder =  node;
                break;
            }else{
                return getRootForResult(e,target);
            }
        }

        return finder;
    }


}
