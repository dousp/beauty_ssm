package com.yingjun.ssm.webService;

import java.util.List;
import java.util.Map;

/**
 * Created by dou on 2016/11/8.
 */
public interface ResourceService {

    /**
     * 销售人员信息查询
     * @param finder
     * @return
     */
    List<Map<String,Object>> findResource(String finder);

    /**
     * 销售人员信息更新
     * @param finder
     * @return
     */
    List<Map<String,Object>> updateResource(String finder);
}
