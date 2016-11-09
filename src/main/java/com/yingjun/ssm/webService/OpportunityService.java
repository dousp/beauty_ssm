package com.yingjun.ssm.webService;

import java.util.List;
import java.util.Map;

/**
 * Created by dou on 2016/11/8.
 */
public interface OpportunityService {

    /**
     * 合同信息查询
     * @param finder
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findOpportunity(String finder);
}
