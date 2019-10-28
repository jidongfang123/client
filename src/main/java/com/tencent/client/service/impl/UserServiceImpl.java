package com.tencent.client.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tencent.client.enums.Message;
import com.tencent.client.mapper.DbUserMapper;
import com.tencent.client.model.DbUser;
import com.tencent.client.model.DbUserExample;
import com.tencent.client.service.UserService;
import com.tencent.client.util.ResponseVo;
import com.tencent.client.vo.UserRegVo;
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private DbUserMapper dbUserMapper;
	
	@Override
	public ResponseVo selectUserList() {
		ResponseVo responseVo = new ResponseVo();
		DbUserExample example = new DbUserExample();
		DbUserExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo("冀东方");
		List<DbUser> list = dbUserMapper.selectByExample(example);
		responseVo.setData(list);
		return responseVo;
	}

	@Override
	public DbUser selectUserInfo(String userName) {
		DbUserExample example = new DbUserExample();
		DbUserExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(userName);
		return dbUserMapper.selectUserInfoByExample(example);
	}
	@Override
	public ResponseVo insert(UserRegVo user) {
		ResponseVo responseVo = new ResponseVo();
		int count = dbUserMapper.insert(user);
		if (count >0) {
			responseVo.setCode(Message.OK.getCode());
			responseVo.setMessage(Message.OK.getMessage());
			return responseVo;
		}
		responseVo.setCode(Message.ERROR_A.getCode());
		return responseVo;
	}

}
