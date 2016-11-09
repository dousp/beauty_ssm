package com.yingjun.ssm.util;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
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
import java.nio.charset.Charset;

/**
 * Created by dou on 2016/11/9.
 */
public class HttpClientSoapUtil {

    public static int socketTimeout = 30000;// 请求超时时间
    public static int connectTimeout = 50000;// 传输超时时间
    private static Logger logger = Logger.getLogger(HttpClientSoapUtil.class);

    public static String doPostSoap1_1(String postUrl, String soapXml, String soapAction) {

        String responseEntity = "";
        CloseableHttpClient closeableHttpClient = null;
        HttpPost httpPost = null;

        try {
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                                                                .setConnectTimeout(connectTimeout).build();
            // SOAPAction
            StringEntity data = new StringEntity(soapXml, Charset.forName("UTF-8"));
            // basici认证
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new UsernamePasswordCredentials("INTUSR", "Welcome1"));
            // httpclient
            closeableHttpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider)
                                                      .setDefaultRequestConfig(requestConfig).build();
            // post请求
            httpPost = new HttpPost(postUrl);
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            // httpPost.setConfig(requestConfig); //httpclient在上面已设置
            httpPost.setEntity(data);

            // 响应
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {
                // 响应内容
                responseEntity = EntityUtils.toString(httpEntity, "UTF-8");
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
        return responseEntity;
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

}
