package com.yingjun.ssm.webService;

import com.yingjun.ssm.util.SoapFactory;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by dou on 2016/11/9.
 */
public class Main {

    private static final String wsdl = "https://ccgp-test.crm.ap1.oraclecloud.com/crmCommonSalesParties/AccountService?WSDL";

    private static final String OpportunityService_WSDL= "https://ccgp-test.crm.ap1.oraclecloud.com/opptyMgmtOpportunities/OpportunityService?WSDL";

    private static final String soapAction = "http://xmlns.oracle.com/apps/sales/opptyMgmt/opportunities/opportunityService/findOpportunity";

    static int socketTimeout = 30000;// 请求超时时间
    static int connectTimeout = 60000;// 传输超时时间
    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        String ss = doPostSoap1_1(OpportunityService_WSDL,getBody(),soapAction);
        System.out.println(ss);



    }

    public static String doPostSoap1_1(String postUrl, String soapXml, String soapAction) {

        String retStr = "";
        // 创建HttpClientBuilder
        // HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        // CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        CloseableHttpClient closeableHttpClient = null;

        HttpPost httpPost = null;



        try {

            httpPost = new HttpPost(postUrl);

            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
            // SOAPAction
            StringEntity data = new StringEntity(soapXml, Charset.forName("UTF-8"));
            // basici认证
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new UsernamePasswordCredentials("INTUSR", "Welcome1"));

            closeableHttpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider)
                                                      .setDefaultRequestConfig(requestConfig)
                                                      .build();

            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            // httpPost.setConfig(requestConfig);
            httpPost.setEntity(data);


            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                //logger.info("response:" + retStr);
            }
            response.close();

        } catch (Exception e) {
            logger.error("exception in doPostSoap1_1", e);
        } finally {
            // 释放资源
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return retStr;
    }

    public static String doPostSoap1_2(String postUrl, String soapXml, String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type",
                    "application/soap+xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                //logger.info("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            logger.error("exception in doPostSoap1_2", e);
        }
        return retStr;
    }


    public static String getBody() {
        String s = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                + "xmlns:typ=\"http://xmlns.oracle.com/apps/sales/opptyMgmt/opportunities/opportunityService/types/\" "
                + " xmlns:typ1=\"http://xmlns.oracle.com/adf/svc/types/\">"
                + "   <soapenv:Header/>                                                                                                                                                                                                                                      "
                + "   <soapenv:Body>                                                                                                                                                                                                                                          "
                + "      <typ:findOpportunity>                                                                                                                                                                                                                                "
                + "         <typ:findCriteria>                                                                                                                                                                                                                                "
                + "            <typ1:fetchStart>0</typ1:fetchStart>                                                                                                                                                                                                          "
                + "            <typ1:fetchSize>-1</typ1:fetchSize>                                                                                                                                                                                                           "
                + "            <!--Optional:-->                                                                                                                                                                                                                               "
                + "            <typ1:filter>                                                                                                                                                                                                                                  "
                + "               <!--Optional:-->                                                                                                                                                                                                                            "
                + "               <typ1:conjunction>And</typ1:conjunction>                                                                                                                                                                                                   "
                + "               <!--1 or more repetitions:-->                                                                                                                                                                                                               "
                + "               <typ1:group>                                                                                                                                                                                                                                "
                + "                  <!--Optional:-->                                                                                                                                                                                                                         "
                + "                  <typ1:conjunction>And</typ1:conjunction>                                                                                                                                                                                                "
                + "                  <typ1:upperCaseCompare>false</typ1:upperCaseCompare>                                                                                                                                                                                    "
                + "                  <!--1 or more repetitions:-->                                                                                                                                                                                                            "
                + "                  <typ1:item>                                                                                                                                                                                                                              "
                + "                     <!--Optional:-->                                                                                                                                                                                                                      "
                + "                     <typ1:conjunction>And</typ1:conjunction>                                                                                                                                                                                             "
                + "                     <typ1:upperCaseCompare>false</typ1:upperCaseCompare>                                                                                                                                                                                 "
                + "                     <typ1:attribute>LastUpdateDate</typ1:attribute>                                                                                                                                                                                      "
                + "                     <typ1:operator>AFTER</typ1:operator>                                                                                                                                                                                                 "
                + "                     <!--You have a CHOICE of the next 2 items at this level-->                                                                                                                                                                            "
                + "                     <!--Zero or more repetitions:-->                                                                                                                                                                                                      "
                + "                     <typ1:value>2016-10-30T04:14:38.948Z</typ1:value>                                                                                                                                                                                    "
                + "                                                                                                                                                                                                                                                           "
                + "                  </typ1:item>                                                                                                                                                                                                                            "
                + "               </typ1:group>                                                                                                                                                                                                                              "
                + "               <!--Zero or more repetitions:-->                                                                                                                                                                                                            "
                + "                                                                                                                                                                                                                                                           "
                + "            </typ1:filter>                                                                                                                                                                                                                                "
                + "            <!--Optional:-->                                                                                                                                                                                                                               "
                + "                                                                                                                                                                                                                                                           "
                + "            <!--Zero or more repetitions:-->                                                                                                                                                                                                               "
                + "                                                                                                                                                                                                                                                           "
                + "            <typ1:excludeAttribute>false</typ1:excludeAttribute>                                                                                                                                                                                          "
                + "         </typ:findCriteria>                                                                                                                                                                                                                              "
                + "         <typ:findControl>                                                                                                                                                                                                                                 "
                + "            <typ1:retrieveAllTranslations>false</typ1:retrieveAllTranslations>                                                                                                                                                                            "
                + "         </typ:findControl>                                                                                                                                                                                                                               "
                + "      </typ:findOpportunity>                                                                                                                                                                                                                              "
                + "   </soapenv:Body>                                                                                                                                                                                                                                        "
                + "</soapenv:Envelope>                                                                                                                                                                                                                                       ";
        return s;
    }


}
