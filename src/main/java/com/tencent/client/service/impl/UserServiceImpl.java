package com.tencent.client.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tencent.client.dto.VeriCodeDto;
import com.tencent.client.enums.Message;
import com.tencent.client.mapper.DbUserMapper;
import com.tencent.client.model.DbUser;
import com.tencent.client.model.DbUserExample;
import com.tencent.client.service.UserService;
import com.tencent.client.util.MailUtil;
import com.tencent.client.util.PhoneCodeUntil;
import com.tencent.client.util.RedisUtil;
import com.tencent.client.util.ResponseVo;
import com.tencent.client.vo.UserRegVo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired
  RedisUtil redisUtil;

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
    if (count > 0) {
      responseVo.setCode(Message.OK.getCode());
      responseVo.setMessage(Message.OK.getMessage());
      return responseVo;
    }
    responseVo.setCode(Message.ERROR_A.getCode());
    return responseVo;
  }

  @Override
  public void sendMsg(String phone, String veriCode) {
    String key = phone + veriCode;
    redisUtil.set(key, veriCode, 600);
  }

  @Override
  public ResponseVo sendEmail(String email) {
    ResponseVo responseVo = new ResponseVo();
    String veriCode = PhoneCodeUntil.Random();
    String key = email + veriCode;
    String txt = "您的验证码为" + veriCode + ",有效时间10分钟";
    try {
      MailUtil.sendMail(email, "邮箱验证码", txt);
      redisUtil.set(key, veriCode, 600);
    } catch (UnsupportedEncodingException | GeneralSecurityException e) {
      e.printStackTrace();
    }
    responseVo.setCode(Message.OK.getCode());
    return responseVo;
  }

  @Override
  public ResponseVo veriCode(VeriCodeDto codeDto) {
    ResponseVo responseVo = new ResponseVo();
    String key = codeDto.getNumber()+codeDto.getCode();
    Object b = redisUtil.get(key);
    if (codeDto.getCode().equals(b)) {
      log.info(b.toString()+"key存在");
      redisUtil.del(key);
      responseVo.setCode(Message.OK.getCode());
      responseVo.setMessage("验证成功");
      log.info(b.toString()+"key删除成功");
    }else {
      log.info(key+"不存在");
      responseVo.setCode(Message.FAIL.getCode());
      responseVo.setMessage(Message.ERROR_F.getMessage());
      return responseVo;
    }
    return responseVo;
   
  }

}
