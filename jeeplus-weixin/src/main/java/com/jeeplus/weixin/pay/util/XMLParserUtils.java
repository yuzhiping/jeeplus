package com.jeeplus.weixin.pay.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * XML解析工具类
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 18:50.
 */
public class XMLParserUtils {


    /**
     * 将XML转换成一个Map对象
     * @param xmlString
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static SortedMap<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        InputStream inputStream= WeiXinPayUtils.getStringStream(xmlString);
        Document document=builder.parse(inputStream);

        //获取到document里面的全部结点
        NodeList nodeList=document.getFirstChild().getChildNodes();
        Node node;
        SortedMap<String, Object> sortedMap=new TreeMap<String,Object>();
        int i=0;
        while (i < nodeList.getLength()) {
            node = nodeList.item(i);
            if(node instanceof Element){
                sortedMap.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return sortedMap;
    }

}
