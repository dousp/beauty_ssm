package com.yingjun.ssm.webService;

import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * Created by dou on 2016/11/8.
 */
public interface AccountService {

    /**
     * 客户信息查询
     * @param finder
     * @return
     */
    List<Map<String,Object>> findAccount(String finder) throws DocumentException;
}
