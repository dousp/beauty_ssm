package com.yingjun.ssm.webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ClientTest {
	public static void main(String[] args) throws Exception {
//		JaxWsDynamicClientFactory  factory =JaxWsDynamicClientFactory.newInstance();
//	    Client client =factory.createClient("https://ccgp-test.crm.ap1.oraclecloud.com/opptyMgmtExtensibility/SalesCustomObjectService?WSDL");
//	    Object[] o = client.invoke("findEntity", getBody());
//	    System.out.println(o[0]);
		httpClient();
	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void httpClient() throws ClientProtocolException, IOException {
		 String soapRequestData = getBody();

	        System.out.println(soapRequestData);

	        HttpPost request = new HttpPost("https://ccgp-test.crm.ap1.oraclecloud.com:443/opptyMgmtOpportunities/OpportunityService");

	        // 然后把Soap请求数据添加到PostMethod中
	        StringEntity entity = new StringEntity(soapRequestData);
	        request.setEntity(entity);

	        // 最后生成一个HttpClient对象，并发出postMethod请求

	        CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new UsernamePasswordCredentials("INTUSR", "Welcome1"));
	        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
	        request.setHeader("Content-Type","text/xml;charset=UTF-8");
	        request.setHeader("SOAPAction", "http://xmlns.oracle.com/apps/sales/opptyMgmt/opportunities/opportunityService/findOpportunity");
	        HttpResponse response = httpclient.execute(request);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        StringBuilder sb = new StringBuilder();
	        String line ;
	        while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
	        System.out.println(response.getStatusLine().getStatusCode());
	        System.out.println(sb.toString());
	        if(response.getStatusLine().getStatusCode() == 200) {
	        	System.out.println(response.getEntity().getContent());
	        }else {
	        }
	}

	public static String getBody () {
		String s ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "xmlns:typ=\"http://xmlns.oracle.com/apps/sales/opptyMgmt/opportunities/opportunityService/types/\" "
				+ " xmlns:typ1=\"http://xmlns.oracle.com/adf/svc/types/\">"
				+"   <soapenv:Header/>                                                                                                                                                                                                                                      "
				+"   <soapenv:Body>                                                                                                                                                                                                                                          "
				+"      <typ:findOpportunity>                                                                                                                                                                                                                                "
				+"         <typ:findCriteria>                                                                                                                                                                                                                                "
				+"            <typ1:fetchStart>0</typ1:fetchStart>                                                                                                                                                                                                          "
				+"            <typ1:fetchSize>-1</typ1:fetchSize>                                                                                                                                                                                                           "
				+"            <!--Optional:-->                                                                                                                                                                                                                               "
				+"            <typ1:filter>                                                                                                                                                                                                                                  "
				+"               <!--Optional:-->                                                                                                                                                                                                                            "
				+"               <typ1:conjunction>And</typ1:conjunction>                                                                                                                                                                                                   "
				+"               <!--1 or more repetitions:-->                                                                                                                                                                                                               "
				+"               <typ1:group>                                                                                                                                                                                                                                "
				+"                  <!--Optional:-->                                                                                                                                                                                                                         "
				+"                  <typ1:conjunction>And</typ1:conjunction>                                                                                                                                                                                                "
				+"                  <typ1:upperCaseCompare>false</typ1:upperCaseCompare>                                                                                                                                                                                    "
				+"                  <!--1 or more repetitions:-->                                                                                                                                                                                                            "
				+"                  <typ1:item>                                                                                                                                                                                                                              "
				+"                     <!--Optional:-->                                                                                                                                                                                                                      "
				+"                     <typ1:conjunction>And</typ1:conjunction>                                                                                                                                                                                             "
				+"                     <typ1:upperCaseCompare>false</typ1:upperCaseCompare>                                                                                                                                                                                 "
				+"                     <typ1:attribute>LastUpdateDate</typ1:attribute>                                                                                                                                                                                      "
				+"                     <typ1:operator>AFTER</typ1:operator>                                                                                                                                                                                                 "
				+"                     <!--You have a CHOICE of the next 2 items at this level-->                                                                                                                                                                            "
				+"                     <!--Zero or more repetitions:-->                                                                                                                                                                                                      "
				+"                     <typ1:value>2016-10-30T04:14:38.948Z</typ1:value>                                                                                                                                                                                    "
				+"                                                                                                                                                                                                                                                           "
				+"                  </typ1:item>                                                                                                                                                                                                                            "
				+"               </typ1:group>                                                                                                                                                                                                                              "
				+"               <!--Zero or more repetitions:-->                                                                                                                                                                                                            "
				+"                                                                                                                                                                                                                                                           "
				+"            </typ1:filter>                                                                                                                                                                                                                                "
				+"            <!--Optional:-->                                                                                                                                                                                                                               "
				+"                                                                                                                                                                                                                                                           "
				+"            <!--Zero or more repetitions:-->                                                                                                                                                                                                               "
				+"                                                                                                                                                                                                                                                           "
				+"            <typ1:excludeAttribute>false</typ1:excludeAttribute>                                                                                                                                                                                          "
				+"         </typ:findCriteria>                                                                                                                                                                                                                              "
				+"         <typ:findControl>                                                                                                                                                                                                                                 "
				+"            <typ1:retrieveAllTranslations>false</typ1:retrieveAllTranslations>                                                                                                                                                                            "
				+"         </typ:findControl>                                                                                                                                                                                                                               "
				+"      </typ:findOpportunity>                                                                                                                                                                                                                              "
				+"   </soapenv:Body>                                                                                                                                                                                                                                        "
				+"</soapenv:Envelope>                                                                                                                                                                                                                                       ";
	    return s;
	}

}
