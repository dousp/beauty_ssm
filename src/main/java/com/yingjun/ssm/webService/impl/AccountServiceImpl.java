package com.yingjun.ssm.webService.impl;

import com.yingjun.ssm.util.Dom4jUtil;
import com.yingjun.ssm.util.HttpClientSoapUtil;
import com.yingjun.ssm.util.SoapFactory;
import com.yingjun.ssm.webService.AccountService;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * Created by dou on 2016/11/9.
 */
public class AccountServiceImpl implements AccountService {

    @Override
    public List<Map<String, Object>> findAccount(String finder) throws DocumentException {

        String string = HttpClientSoapUtil.doPostSoap1_1(SoapFactory.WSDL_AccountService,finder,SoapFactory.SA_AccountService_findAccount);

        return Dom4jUtil.getResultByString(string);

    }
}
