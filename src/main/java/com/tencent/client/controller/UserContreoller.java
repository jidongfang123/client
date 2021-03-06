package com.tencent.client.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tencent.client.config.Log;
import com.tencent.client.config.LogAsPect;
import com.tencent.client.dto.VeriCodeDto;
import com.tencent.client.enums.Message;
import com.tencent.client.model.DbUser;
import com.tencent.client.service.UserService;
import com.tencent.client.util.PhoneCodeUntil;
import com.tencent.client.util.RedisUtil;
import com.tencent.client.util.ResponseVo;
import com.tencent.client.util.SendSms;
import com.tencent.client.vo.UserLoginVo;
import com.tencent.client.vo.UserRegVo;

@RestController
@RequestMapping("user")
public class UserContreoller {
  @Autowired
  RedisUtil redisUtil;
  @Autowired
  private UserService userService;
  @Autowired
  LogAsPect asPect;

  private final static Logger log = org.slf4j.LoggerFactory.getLogger(UserContreoller.class);

  @PostMapping("selectUserList")
  public ResponseVo selectUserList() {
    return userService.selectUserList();
  }

  /**
   * 用户登录
   * 
   * @param user
   * @return
   */
  @PostMapping("login")
  public ResponseVo login(@RequestBody UserLoginVo user) {
    log.info("我被执行了！");
    ResponseVo responseVo = new ResponseVo();
    String userName = user.getUserName();
    String pwd = user.getPassword();
    if (StringUtils.isEmpty(userName)) {
      responseVo.setMessage(Message.ERROR_A.getMessage());
      return responseVo;
    }
    if (StringUtils.isEmpty(pwd)) {
      responseVo.setMessage(Message.ERROR_B.getMessage());
      return responseVo;
    }
    DbUser userInfo = userService.selectUserInfo(userName);
    if (StringUtils.isEmpty(userInfo)) {
      responseVo.setMessage(Message.ERROR_C.getMessage());
      responseVo.setCode(Message.ERROR_C.getCode());

      return responseVo;
    }
    if (userInfo.getPassword().equals(DigestUtils.md5DigestAsHex(pwd.getBytes()))) {
      responseVo.setMessage(Message.OK.getMessage());
      userInfo.setPassword("***");
      responseVo.setData(userInfo);
      redisUtil.lSet(userInfo.getName(), userInfo);
      return responseVo;
    } else {
      responseVo.setMessage(Message.ERROR_D.getMessage());
      return responseVo;

    }
  }

  /**
   * 注册用户
   * 
   * @param user
   * @return
   */
  @PostMapping("register")
  public ResponseVo register(@RequestBody UserRegVo user) {
    ResponseVo responseVo = new ResponseVo();
    String userName = user.getName();
    String pwd = user.getPassword();
    if (StringUtils.isEmpty(userName)) {
      responseVo.setMessage(Message.ERROR_A.getMessage());
      return responseVo;
    }
    if (StringUtils.isEmpty(pwd)) {
      responseVo.setMessage(Message.ERROR_B.getMessage());
      return responseVo;
    }
    DbUser userInfo = userService.selectUserInfo(userName);
    if (!StringUtils.isEmpty(userInfo)) {
      responseVo.setMessage(Message.ERROR_E.getMessage());
      responseVo.setCode(Message.ERROR_E.getCode());
      return responseVo;
    }
    user.setPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
    return userService.insert(user);
  }

  /**
   * 阿里云短信验证码
   * 
   * @param phone
   * @return
   */
  @PostMapping("sendMsg")
  public ResponseVo sendMsg(@RequestBody String phone) {
    ResponseVo responseVo = new ResponseVo();
    if (StringUtils.isEmpty(phone)) {
      responseVo.setMessage(Message.ERROR_A.getMessage());
      return responseVo;
    }
    String veriCode = PhoneCodeUntil.Random();
    userService.sendMsg(phone, veriCode);
    return SendSms.aliyunSendSms(phone, veriCode);

  }

  /**
   * 发送邮件
   * 
   * @param email
   * @return
   */
  @PostMapping("sendEmail")
  public ResponseVo sendEmail(@RequestBody String email) {
    ResponseVo responseVo = new ResponseVo();
    if (StringUtils.isEmpty(email)) {
      responseVo.setMessage(Message.ERROR_A.getMessage());
      return responseVo;
    }
    return userService.sendEmail(email);

  }

  @PostMapping("veriCode")
  public ResponseVo veriCode(@RequestBody VeriCodeDto codeDto) {
    ResponseVo responseVo = new ResponseVo();
    if (StringUtils.isEmpty(codeDto)) {
      responseVo.setMessage(Message.ERROR_A.getMessage());
      return responseVo;
    }
    return userService.veriCode(codeDto);
  }
}
