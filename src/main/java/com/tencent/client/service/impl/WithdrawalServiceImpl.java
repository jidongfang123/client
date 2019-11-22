package com.tencent.client.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.tencent.client.mapper.WithdrawalMapper;
import com.tencent.client.service.WithdrawalService;
import com.tencent.client.util.HttpUtil;
import com.tencent.client.util.MD5;
import com.tencent.client.util.ResponseVo;
@Service
public class WithdrawalServiceImpl implements WithdrawalService {
  @Resource
  WithdrawalMapper withdrawalMapper;
  @Override
  public ResponseVo withdrawalCaseCount() {
    ResponseVo responseVo = new ResponseVo();
    Map<String, Object> map = new  HashMap<>();
    String url = "http://106.15.38.154:8080/court/upload/mediateCase";
    long time = System.currentTimeMillis();
    String key = "A8EVzQJtA8rKE754AxPqh"+time;
    String token = MD5.md5(key);
    List<Map<String,Object>> data = withdrawalMapper.withdrawalCaseCount();
    map.put("token", token);
    map.put("timestamp", time);
    map.put("data",data);
    JSONObject json = new JSONObject(map);
    String response =  HttpUtil.http(url, json);
    responseVo.setData(response);
    return responseVo;
  }
  
  @Override
  public ResponseVo executionNode() {
    ResponseVo responseVo = new ResponseVo();
    Map<String, Object> map = new  HashMap<>();
    String url = "http://106.15.38.154:8080/court/upload/nodeCase";
    long time = System.currentTimeMillis();
    String key = "A8EVzQJtA8rKE754AxPqh"+time;
    String token = MD5.md5(key);
    List<Map<String,Object>> data = withdrawalMapper.executionNode();
    map.put("token", token);
    map.put("timestamp", time);
    map.put("data",data);
    JSONObject json = new JSONObject(map);
    String response =  HttpUtil.http(url, json);
    responseVo.setData(response);
    return responseVo;
  }

  @Override
  public ResponseVo distributionPartie() {
    ResponseVo responseVo = new ResponseVo();
    Map<String, Object> map = new  HashMap<>();
    String url = "http://106.15.38.154:8080/court/upload/distributionPartie";
    long time = System.currentTimeMillis();
    String key = "A8EVzQJtA8rKE754AxPqh"+time;
    String token = MD5.md5(key);
    List<Map<String,Object>> data = withdrawalMapper.distributionPartie();
    map.put("token", token);
    map.put("timestamp", time);
    map.put("data",data);
    JSONObject json = new JSONObject(map);
    String response =  HttpUtil.http(url, json);
    responseVo.setData(response);
    return responseVo;
  }

}
