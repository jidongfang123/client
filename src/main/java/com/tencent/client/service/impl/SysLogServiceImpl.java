package com.tencent.client.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencent.client.mapper.SysLogMapper;
import com.tencent.client.model.SysLog;
import com.tencent.client.service.ISysLogService;

@Service
public class SysLogServiceImpl implements ISysLogService {

	 	@Resource
	    private SysLogMapper sysLogMapper;
	    
	    @Override
	    public int insertLog(SysLog entity) {
	        // TODO Auto-generated method stub
	        return sysLogMapper.insert(entity);
	    }

}
