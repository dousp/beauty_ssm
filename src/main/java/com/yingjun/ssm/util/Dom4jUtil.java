package com.yingjun.ssm.util;

import org.dom4j.*;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dou on 2016/11/7.
 */
public class Dom4jUtil {

    public static void main(String[] args) throws Exception
    {
        //getXml();
        //parseXML();

        parseOppoXML();

    }


    public static void parseOppoXML() throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File("ws-response.xml"));

        System.out.println("=====================================");
        Element root = doc.getRootElement(); // findOpportunityResponse
        Element e1 = root.element("result");
        listNodes(e1);

        // List childList = root.elements(); // 8
        // System.out.println("childList:"+childList.size());
        //
        //
        //
        //
        // List childList2 = root.elements("result");
        // System.out.println("childList2:"+childList2.size());
        //
        // Element e1 = root.element("result");
        // System.out.println(e1.attributeValue("Opportunity"));
        //
        // for(Iterator iter = root.elementIterator(); iter.hasNext();)
        // {
        //     Element e = (Element)iter.next();
        //
        // }




        System.out.println("=====================================");
    }


        /**
     * 遍历当前节点元素下面的所有(元素的)子节点
     *
     * @param node
     */
    public static void listNodes(Element node) {

        List<Attribute> list = node.attributes();
        // 遍历属性节点
        // for (Attribute attr : list) {
        //     System.out.println(attr.getText() + "---" + attr.getName() + "---" + attr.getValue());
        // }

        System.out.println(node.getName());


        // if (!(node.getTextTrim().equals(""))) {
        //     System.out.println("文本内容：：：：" + node.getText());
        // }else {
        //     System.out.println(node.getName());
        // }

        // 当前节点下面子节点迭代器
        Iterator<Element> it = node.elementIterator();
        // 遍历
        while (it.hasNext()) {
            // 获取某个子节点对象
            Element e = it.next();
            // 对子节点进行遍历
            listNodes(e);
        }

    }


    /**
     * 遍历当前节点元素下面的所有(元素的)子节点
     *
     * @param node
     */
    public static void listNodesDemo(Element node) {
        System.out.println("当前节点的名称：：" + node.getName());
        // 获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        // 遍历属性节点
        for (Attribute attr : list) {
            System.out.println(attr.getText() + "-----" + attr.getName()
                    + "---" + attr.getValue());
        }

        if (!(node.getTextTrim().equals(""))) {
            System.out.println("文本内容：：：：" + node.getText());
        }

        // 当前节点下面子节点迭代器
        Iterator<Element> it = node.elementIterator();
        // 遍历
        while (it.hasNext()) {
            // 获取某个子节点对象
            Element e = it.next();
            // 对子节点进行遍历
            listNodesDemo(e);
        }
    }





    public static void parseXML() throws DocumentException, ParserConfigurationException, IOException, SAXException {

        SAXReader saxReader = new SAXReader();

        Document doc = saxReader.read(new File("student2.xml"));

        Element root = doc.getRootElement();

        System.out.println("root element: " + root.getName());

        List childList = root.elements();

        System.out.println("elements:"+childList.size());

        List childList2 = root.elements("hello");

        System.out.println(childList2.size());

        Element first = root.element("hello");

        System.out.println(first.attributeValue("age"));

        for(Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element e = (Element)iter.next();

            System.out.println(e.attributeValue("age"));
        }

        System.out.println("---------------------------");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        org.w3c.dom.Document document = db.parse(new File("student2.xml"));

        DOMReader domReader = new DOMReader();
        //将JAXP的Document转换为dom4j的Document
        Document d = domReader.read(document);
        Element rootElement = d.getRootElement();
        System.out.println(rootElement.getName());
    }

    public static void getXml() throws IOException {

        // 创建文档并设置文档的根元素节点 ：第一种方式
        // Document document = DocumentHelper.createDocument();
        //
        // Element root = DocumentHelper.createElement("student");
        //
        // document.setRootElement(root);

        // 创建文档并设置文档的根元素节点 ：第二种方式
        Element root = DocumentHelper.createElement("student");
        Document document = DocumentHelper.createDocument(root);

        root.addAttribute("name", "zhangsan");

        Element helloElement = root.addElement("hello");
        Element worldElement = root.addElement("world");

        helloElement.setText("hello");
        worldElement.setText("world");

        helloElement.addAttribute("age", "20");
        worldElement.addAttribute("age", "30");

        OutputFormat format = new OutputFormat("    ", true);

        XMLWriter xmlWriter = new XMLWriter(format);
        xmlWriter.write(document);
        System.out.println();

        XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("student2.xml"), format);
        xmlWriter2.write(document);
        xmlWriter2.close();

        //XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("student3.xml"), format);
        //xmlWriter3.write(document);
        //System.out.println(xmlWriter3.toString());
        //xmlWriter3.close();

    }


}
