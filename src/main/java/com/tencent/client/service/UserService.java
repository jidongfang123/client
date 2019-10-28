package com.tencent.client.service;

import com.tencent.client.model.DbUser;
import com.tencent.client.util.ResponseVo;
import com.tencent.client.vo.UserRegVo;

public interface UserService {

	ResponseVo selectUserList();

	DbUser selectUserInfo(String userName);

	ResponseVo insert(UserRegVo user);

	void sendMsg(String phone, String veriCode);

}
