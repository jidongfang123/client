package com.tencent.client.service;

import com.tencent.client.model.SysLog;

/**
* @author Promise
* @createTime 2018年12月18日 下午9:29:48
* @description 日志接口
*/
public interface ISysLogService {

    /**
     * 插入日志
     * @param entity
     * @return
     */
    int insertLog(SysLog entity);
}
