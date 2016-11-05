package com.yingjun.ssm.webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ClientTest {

    private static final String SalesCustomObjectService_findEntity =

                // "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                // +" xmlns:typ=\"http://xmlns.oracle.com/apps/sales/custExtn/extnService/types/\" "
                // +" xmlns:typ1=\"http://xmlns.oracle.com/adf/svc/types/\"> "
                // +" <soapenv:Header />"
                // +" <soapenv:Body>"
                 "  <typ:findEntity xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                                   "xmlns:typ=\"http://xmlns.oracle.com/apps/sales/custExtn/extnService/types/\" " +
                                   "xmlns:typ1=\"http://xmlns.oracle.com/adf/svc/types/\">"
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
                // +"</soapenv:Body>"
                // +"</soapenv:Envelope>"
                ;

    private static final String OpportunityService_findOpportunity =
				"      <typ:findOpportunity xmlns:typ=\"http://xmlns.oracle.com/apps/sales/opptyMgmt/opportunities/opportunityService/types/\"  xmlns:typ1=\"http://xmlns.oracle.com/adf/svc/types/\">                                                                                                                                                                                                                               "
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
				;

    private static final String SalesCustomObjectService_WSDL = "https://ccgp-test.crm.ap1.oraclecloud.com/opptyMgmtExtensibility/SalesCustomObjectService?WSDL";
    private static final String OpportunityService_WSDL= "https://ccgp-test.crm.ap1.oraclecloud.com/opptyMgmtOpportunities/OpportunityService?WSDL";


    public static void main(String[] args) throws Exception {

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/spring/spring-ws.xml");
        WebServiceTemplate webServiceTemplate = (WebServiceTemplate) ac.getBean("webServiceTemplate");

        try {

            StreamSource source = new StreamSource(new StringReader(OpportunityService_findOpportunity));
            StreamResult result = new StreamResult(System.out);
            webServiceTemplate.sendSourceAndReceiveToResult(OpportunityService_WSDL,source, result);
            System.out.println(result);

            StreamSource source2 = new StreamSource(new StringReader(SalesCustomObjectService_findEntity));
            StreamResult result2 = new StreamResult(System.out);
            webServiceTemplate.sendSourceAndReceiveToResult(SalesCustomObjectService_WSDL,source2, result2);
            System.out.println(result2);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }




        //httpClient();
    }

    /**
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void httpClient() throws IOException {
        String soapRequestData = getBody();
        // String soapRequestData = testMSg;

        System.out.println(soapRequestData);

        HttpPost request = new HttpPost("https://ccgp-test.crm.ap1.oraclecloud.com:443/opptyMgmtOpportunities/OpportunityService");

        // 然后把Soap请求数据添加到PostMethod中
        StringEntity entity = new StringEntity(soapRequestData);
        request.setEntity(entity);

        // 最后生成一个HttpClient对象，并发出postMethod请求

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new UsernamePasswordCredentials("INTUSR", "Welcome1"));
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        request.setHeader("Content-Type", "text/xml;charset=UTF-8");
        request.setHeader("SOAPAction", "http://xmlns.oracle.com/apps/sales/opptyMgmt/opportunities/opportunityService/findOpportunity");
        HttpResponse response = httpclient.execute(request);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(sb.toString());
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println(response.getEntity().getContent());
        } else {
        }
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
